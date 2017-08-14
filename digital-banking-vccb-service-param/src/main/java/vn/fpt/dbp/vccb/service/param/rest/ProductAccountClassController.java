package vn.fpt.dbp.vccb.service.param.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.currency.repository.CurrencyRepository;
import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.interest.repository.TimeRateRepository;
import vn.fpt.dbp.vccb.core.domain.product.DepositType;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.QProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.repository.DepositTypeRepository;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductAccountClassRepository;
import vn.fpt.util.rest.PagedResource;

import javax.xml.namespace.QName;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class ProductAccountClassController {

    @Autowired
    protected ProductAccountClassRepository productAccountClassRepository;

    @Autowired
    protected DepositTypeRepository depositTypeRepository;

    @Autowired
    protected TimeRateRepository timeRateRepository;

    @Autowired
    protected CurrencyRepository currencyRepository;

    @Value("${dbp.osb-gateway-url}")
    private String OSBGateway;

    public static final Logger logger = LoggerFactory.getLogger(ProductAccountClassController.class);

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/productaccountclass/list", method = RequestMethod.GET, produces = "application/json")
    public List<ProductAccountClass> list(Principal principle) {
        return productAccountClassRepository.findAll();
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/productaccountclass/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<ProductAccountClass> search(Principal principle, @RequestBody ProductAccountClass productAccountClass, Pageable pageable) {
        QProductAccountClass qProductAccountClass = QProductAccountClass.productAccountClass;
        BooleanExpression booleanExpression = qProductAccountClass.id.isNotNull();

        Page<ProductAccountClass> result = productAccountClassRepository.findAll(booleanExpression, pageable);

        return new PagedResource<ProductAccountClass>(result.getContent(), result.getNumber(), result.getSize(),
                result.getTotalPages());
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/productaccountclass/osb/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<ProductAccountClass> searchExchangeOSB(Principal principle, @RequestBody Product product, Pageable pageable) {

        String depositTypeCode = null;

        Page<ProductAccountClass> resultPage = null;
        List<ProductAccountClass> productAccountClassList = new ArrayList<ProductAccountClass>();

        try {
            QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
            URL wsdlURL = new URL(OSBGateway);
            com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
            com.alsb.WebService port = ss.getWebService();

            if (product.getDepositType() != null) {
                DepositType depositType = depositTypeRepository.findOne(product.getDepositType().getId());
                if(depositType != null){
                    depositTypeCode = depositType.getCode();
                }
            }

            com.alsb.CoreACSearchResponse.AccountClassList accountClassListResponse = port.coreACSearch(depositTypeCode, null, null);

            for (com.alsb.CoreACSearchResponse.AccountClassList.AccountClass item : accountClassListResponse.getAccountClass() ) {
                ProductAccountClass productAccountClassTmp = new ProductAccountClass();

                productAccountClassTmp.setCode(item.getACCode());
                productAccountClassTmp.setName(item.getACName());
                //Kỳ hạn
                TimeRate timeRateTmp = timeRateRepository.findByCode(item.getTerm());
                productAccountClassTmp.setTimeRate(timeRateTmp);

                Currency currencyTmp = currencyRepository.findByCode(item.getCCY());
                productAccountClassTmp.setCurrency(currencyTmp);
                //Định kỳ gửi
                if(StringUtils.isNotEmpty(item.getDinhKyGui().trim())) {
                    productAccountClassTmp.setPeriodDeposit(Double.parseDouble(item.getDinhKyGui()));
                }
                //Số tiền gửi tối thiểu cho mỗi định kỳ gửi
                if(StringUtils.isNotEmpty(item.getRdMinIns().trim())) {
                    productAccountClassTmp.setPeriodDepositMin(Double.parseDouble(item.getRdMinIns()));
                }
                //Số tiền gửi tối thiểu
                if(StringUtils.isNotEmpty(item.getMinAmt().trim())) {
                    productAccountClassTmp.setDepositMin(Double.parseDouble(item.getMinAmt()));
                }
                //Sản phẩm chuyển khi đáo hạn
                if(StringUtils.isNotEmpty(item.getACChuyenKhiDaoHan().trim())) {
                    productAccountClassTmp.setMatureToAccountClass(item.getACChuyenKhiDaoHan());
                }
                //Hình thức đáo hạn
                productAccountClassTmp.setMatureForm(item.getHinhThucDaoHan());
                //Số dư duy trì tối thiểu
                if(StringUtils.isNotEmpty(item.getMinRemainingBalance().trim())) {
                    productAccountClassTmp.setBalanceMin(Double.parseDouble(item.getMinRemainingBalance()));
                }

                productAccountClassList.add(productAccountClassTmp);
            }

            if (productAccountClassList == null || productAccountClassList.size() <= 0) {
                return new PagedResource<ProductAccountClass>(new ArrayList<ProductAccountClass>(), 0,0,0,0);
            } else {
                resultPage = new PageImpl<ProductAccountClass>(productAccountClassList, pageable, productAccountClassList.size());
            }

            return new PagedResource<ProductAccountClass>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
                    resultPage.getTotalPages(), resultPage.getTotalElements());
        } catch (Exception e) {
            logger.error("Error at service /api/productaccountclass/osb/search : " + e.getMessage());
            return new PagedResource<ProductAccountClass>(new ArrayList<ProductAccountClass>(), 0,0,0,0);
        }
    }


}
