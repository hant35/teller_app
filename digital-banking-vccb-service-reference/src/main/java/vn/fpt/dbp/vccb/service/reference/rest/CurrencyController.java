package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.currency.repository.CurrencyRepository;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductRepository;
import vn.fpt.dbp.vccb.core.domain.currency.QCurrency;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;


@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CurrencyController {
	
	//@Autowired
	//private EventBus eventBus;

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
    protected ProductRepository productRepository;
	
	@RequestMapping(value = "/api/currency/list", method = RequestMethod.GET, produces = "application/json")
	public List<Currency> listCurrencys(Principal principle) {
		return currencyRepository.findAll();
	}
	
	@RequestMapping(value = "/api/currency/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Currency currencyInfo(Principal principle, @PathVariable("id") Long id) {
		Currency Currency = currencyRepository.findOne(id);
		
		return Currency;
	}
	
	@RequestMapping(value = "/api/currency/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Currency> searchCurrency(Principal principle, @RequestBody Currency currency, Pageable pageable) {
		QCurrency qCurrency = QCurrency.currency;
		BooleanExpression booleanExpression = qCurrency.name.like("%");
		if (StringUtils.isNotEmpty(currency.getCode())) {
			booleanExpression = booleanExpression.and(qCurrency.code.upper().like(currency.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(currency.getName())) {
			booleanExpression = booleanExpression.and(qCurrency.name.upper().like(currency.getName().toUpperCase()));
		}
		
		Page<Currency> result = currencyRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Currency>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
		
	}
	
	@RequestMapping(value = "/api/currency/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Currency> createCurrency(Principal principle, @RequestBody @Valid Currency request) {
		RestResponse<Currency> resp = new RestResponse<Currency>();
		try {
			Currency currency = request.getId() == null ? null : currencyRepository.findOne(request.getId());
			if (currency != null) {
				resp.setStatus(RestResponse.STATUS_ERROR);
				resp.setErrorMessage("This record is exist");
				resp.setContent(request);
			}
			else {
				currencyRepository.save(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(request);
				
//				CurrencyCreatedEvent event = new CurrencyCreatedEvent(request);
//				eventBus.publish(asEventMessage(event));
			}
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@RequestMapping(value = "/api/currency/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Currency> modifyCurrency(Principal principle, @RequestBody @Valid Currency request) {
		RestResponse<Currency> resp = new RestResponse<Currency>();
		try {
			NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			Currency currency = currencyRepository.findOne(request.getId());
			beanUtils.copyProperties(currency, request);
			
			currencyRepository.save(currency);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(currency);
			
//			CurrencyModifiedEvent event = new CurrencyModifiedEvent(currency);
//			eventBus.publish(asEventMessage(event));
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@RequestMapping(value = "/api/currency/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Currency> deleteCurrency(Principal principle, @RequestBody @Valid Currency request) {
		RestResponse<Currency> resp = new RestResponse<Currency>();
		try {
			Currency deletedCurrency = currencyRepository.findOne(request.getId());
			
			currencyRepository.save(deletedCurrency);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(deletedCurrency);
			
//			CurrencyDeletedEvent event = new CurrencyDeletedEvent(deletedCurrency);
//			eventBus.publish(asEventMessage(event));
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
    //Input: Product ID
    //      Currency Code
    //Output: set<Currency>
	@CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/currency/searchbyproduct", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<Currency> searchCurrencyByProduct(Principal principle, @RequestBody @Valid Product request, Pageable pageable) {
        Set<Currency> currencySet = new HashSet<Currency>();

        Product product = null;
        Currency currency = null;
        Currency currencyRequest = null;

        if(request.getId() != null)
        {
            product = productRepository.findOne(request.getId());
        }

        if( request.getProductAccountClasses() != null && request.getProductAccountClasses().size() > 0) {
            for (ProductAccountClass productAccountClass : request.getProductAccountClasses()) {
                currencyRequest = productAccountClass.getCurrency();
                break;
            }
        }

        if(product != null ) {
            if (product.getProductAccountClasses() != null && product.getProductAccountClasses().size() > 0) {
                for (ProductAccountClass productAccountClass : product.getProductAccountClasses()
                        ) {
                    if (productAccountClass.getCurrency() != null) {
                        if(productAccountClass.getCurrency().getCode() != null && currencyRequest != null) {
                            if(currencyRequest.getCode() != null) {
                                if (productAccountClass.getCurrency().getCode().toUpperCase().contains(currencyRequest.getCode().toUpperCase())) {
                                    currencySet.add(productAccountClass.getCurrency());
                                }
                            }
                        }else {
//                          currency = currencyRepository.findOne(productAccountClass.getCurrency().getId());
                            currencySet.add(productAccountClass.getCurrency());
                        }
                    }
                }
            }
        }
        if(currencySet == null && currencySet.size() <= 0)
        {
            return  new PagedResource<Currency>(new ArrayList<Currency>() , pageable.getPageNumber(), pageable.getPageSize(),0);
        }

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > currencySet.size() ? currencySet.size() : (start + pageable.getPageSize());
        int totalPages = (int) Math.ceil((double) currencySet.size() / pageable.getPageSize());

        List<Currency> currencyList = new ArrayList<Currency>(currencySet);
        try {
            PagedResource<Currency> currencyPagedResource = new PagedResource<Currency>(currencyList.subList(start, end), pageable.getPageNumber(), pageable.getPageSize(), totalPages);
            return currencyPagedResource;
        }catch (Exception e){
            return  new PagedResource<Currency>(new ArrayList<Currency>() , pageable.getPageNumber(), pageable.getPageSize(),0);
        }


    }
}
