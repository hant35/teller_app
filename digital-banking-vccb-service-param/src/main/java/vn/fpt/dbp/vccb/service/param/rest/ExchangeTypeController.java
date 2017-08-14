package vn.fpt.dbp.vccb.service.param.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeType;
import vn.fpt.dbp.vccb.core.domain.exchange.QExchangeType;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeTypeRepository;

import vn.fpt.util.rest.PagedResource;

import javax.xml.namespace.QName;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class ExchangeTypeController {
    @Autowired
    protected ExchangeTypeRepository exchangeTypeRepository;

    @Value("${dbp.osb-gateway-url}")
    private String OSBGateway;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/exchangetype/list", method = RequestMethod.GET, produces = "application/json")
    public List<ExchangeType> listExchangeType(Principal principle) {
        return exchangeTypeRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/exchangetype/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public ExchangeType exchangeTypeInfo(Principal principle, @PathVariable("id") Long id) {
        ExchangeType exchangeType = exchangeTypeRepository.findOne(id);

        return exchangeType;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/exchangetype/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<ExchangeType> searchExchangeType(Principal principle, @RequestBody ExchangeType exchangeType, Pageable pageable) {
        QExchangeType qAutoBlockType = QExchangeType.exchangeType;
        BooleanExpression booleanExpression = qAutoBlockType.id.isNotNull();

        Page<ExchangeType> result = exchangeTypeRepository.findAll(booleanExpression, pageable);

        return new PagedResource<ExchangeType>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/exchangetype/osb/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<ExchangeType> searchExchangeTypeOSB(Principal principle, @RequestBody ExchangeType exchangeType, Pageable pageable) {

        Page<ExchangeType> resultPage = null;
        List<ExchangeType> exchangeTypeList = new ArrayList<ExchangeType>();

        try {
            QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
            URL wsdlURL = new URL(OSBGateway);
            com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
            com.alsb.WebService port = ss.getWebService();

            com.alsb.CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList exchangeRateTypeList = port.coreExchangeRateTypeSearch (null,null);

            for (com.alsb.CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList.ExchangeRateType item : exchangeRateTypeList.getExchangeRateType()) {
                ExchangeType exchangeTypeTemp = new ExchangeType();

                exchangeTypeTemp.setCode(item.getExchangeRateTypeCode());
                exchangeTypeTemp.setName(item.getExchangeRateTypeName());

                exchangeTypeList.add(exchangeTypeTemp);
            }

            if (exchangeTypeList == null || exchangeTypeList.size() < 1) {
                return new PagedResource<ExchangeType>();
            } else {
                resultPage = new PageImpl<ExchangeType>(exchangeTypeList, pageable, exchangeTypeList.size());
            }

            return new PagedResource<ExchangeType>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
                    resultPage.getTotalPages(), resultPage.getTotalElements());
        } catch (Exception e) {
            System.out.println("Error at service /api/exchangetype/osb/search : " + e.getMessage());
            return null;
        }
    }
}