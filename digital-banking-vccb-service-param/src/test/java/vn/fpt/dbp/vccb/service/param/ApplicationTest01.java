package vn.fpt.dbp.vccb.service.param;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.fpt.dbp.vccb.core.domain.product.AccountClass;
import vn.fpt.dbp.vccb.core.domain.product.DepositType;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.product.ProductService;
import vn.fpt.dbp.vccb.service.param.Application;
import vn.fpt.util.rest.PagedResource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by T450 on 4/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ApplicationTest01.class)
public class ApplicationTest01 {

    @Test
    public void Testing(){
        Product product = new Product(1);
        product.setCode("T_1");
        product.setName("Think_1");
//        product.setDepositType(new DepositType(1) );
        Set<AccountClass> accountClassSet = new HashSet<AccountClass>()
        {{
            add(new AccountClass(1));
            add(new AccountClass(2));
        }};
        product.setAccountClasses(accountClassSet);


        System.out.println(product.getDepositType() != null);
        System.out.println(product.getAccountClasses().size());
        System.out.println(product.getAccountClasses() != null );
        System.out.println(product.getProductLimits() != null);

        System.out.println("---------");
        System.out.println(StringUtils.isNotEmpty(product.getCode()));
        System.out.println(StringUtils.isNotEmpty(product.getName()));
        System.out.println(product.getProductCustomers() != null);
        System.out.println(product.getEffectedDate() != null && product.getExpiredDate() != null);
        System.out.println(product.getAgeFrom() != null);
        System.out.println(product.getAgeFrom() != null && product.getAgeTo() != null);
//        System.out.println(StringUtils.isNotEmpty(product.getAgeFrom().toString()));
//        System.out.println(StringUtils.isNotEmpty(product.getAgeFrom().toString()) && StringUtils.isNotEmpty(product.getAgeTo().toString()));



    }
}
