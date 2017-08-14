package vn.fpt.dbp.vccb.service.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.rest.currency.CurrencyService;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

import static org.junit.Assert.assertEquals;

/**
 * Created by NghiaPV on 4/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ApplicationTest01_1.class)
public class ApplicationTest01_1 {

    @Test
    public void Testing(){
        Product product = new Product(1);
        product.setCode("PR4");
        product.setName("Product 4");
        product.setWorkflowStatus("DRAFT");


//        List<Product> productPagedResource = ProductService.findList("http://10.86.202.224/tellerapp/param_his/api/product/list");

//        assertEquals(false,!Status.SAVE_DRAFT.equalsIgnoreCase(product.getWorkflowStatus()));
        if(!Status.SAVE_DRAFT.equalsIgnoreCase(Status.REJECTED)
                && !Status.REJECTED.equalsIgnoreCase(Status.REJECTED)){
            //throw new Exception
        }
        String restUrl = "http://10.86.202.224/tellerapp/param_his/api/product/find";
        Product product1 = new Product(5);

        try {
            PagedResource<Currency> currency = CurrencyService.searchByProduct(restUrl, product1 );
            int count = currency.getContent().size();
            System.out.println(count);
        }catch (Exception e){
            System.out.println(e);
        }


//        User user = userUtil.getUser("linhpp");

    }
}
