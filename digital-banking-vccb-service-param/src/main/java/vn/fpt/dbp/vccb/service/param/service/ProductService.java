package vn.fpt.dbp.vccb.service.param.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitRepository;
import vn.fpt.dbp.vccb.core.domain.product.*;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.DBPRestTemplateFactory;
import vn.fpt.util.rest.RestResponse;

/**
 * Created by NghiaPV5 on 4/18/2017.
 */
@Service
public class ProductService {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product saveAsDraft(Product request) throws Exception {
        Product product = request.getId() == null ? null : productRepository.findOne(request.getId());

        if (product != null) {

            if (!Status.SAVE_DRAFT.equalsIgnoreCase(product.getWorkflowStatus())) {
                throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
            }
        }

//		if (request.getLimitDetails() != null && request.getLimitDetails().size() > 0) {
//			for (LimitDetail lm : request.getLimitDetails()) {
//				lm.setLimit(request);
//			}
//		}
        //request.setWorkflowStatus(Status.SAVE_DRAFT);

        //return limitRepository.save(request);
        return updateProductInfo(request, Status.SAVE_DRAFT);
//        return product;
    }

    @Transactional
    public Product sendForApprove(Product request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        return updateProductInfo(request, Status.SEND_FOR_APPROVE );
    }

    @Transactional
    public Product assign(Product request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if ((request.getAssignee() == null || request.getAssignee().getId() == null) && StringUtils.isEmpty(request.getAssignGroup())){
            throw new Exception(DBPException.NO_ASSIGNEE + " NO_ASSIGN_GROUP");
        }

        return updateProductInfo(request, Status.ASSIGNED);
    }

    @Transactional
    public Product returnAssign(Product request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if ((request.getAssignee() == null || request.getAssignee().getId() == null) && StringUtils.isEmpty(request.getAssignGroup())){
            throw new Exception(DBPException.NO_ASSIGNEE + " NO_ASSIGN_GROUP");
        }

        request.setAssignee(null);
        request.setAssignGroup(null);

        return updateProductInfo(request, Status.SEND_FOR_APPROVE);
    }

    @Transactional(rollbackFor = Exception.class)
    public Product approve(Product request) throws Exception {

//        System.out.print("----Start to approve ");

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
            throw new Exception(DBPException.NO_APPROVER);
        }

        Product originalProduct = productRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(request.getCode(), Status.APPROVED);

        if(request.getOriginalId() == null){
            request.setOriginalId(request.getId());
        }
//        System.out.print("----Start to call  updateProductInfo");
        Product approvedProduct = updateProductInfo(request, Status.APPROVED);

        if(originalProduct != null && approvedProduct != null ) {
//            System.out.println("++++++Start switch Product++++++");

            Long newId = approvedProduct.getId();
            Long originalId = originalProduct.getId();

//            System.out.println("-------- newId = " + newId);
//            System.out.println("-------- originalId = " + originalId);

            swapProduct(newId, originalId);

            //Sau khi swap thi can Get lai approveProduct voi Id của originalId
            approvedProduct = productRepository.findOne(originalId);

//            System.out.println("-------- new approvedProduct = " + approvedProduct);
//            System.out.println("-------- new approvedProduct.getId() = " + approvedProduct.getId());
//            System.out.println("-------- new approvedProduct.getOriginalId() = " + approvedProduct.getOriginalId());
//            System.out.println("-------- new approvedProduct.getLimitDetails() = " + approvedProduct.getAccountClasses());
//            for(AccountClass accountClass : approvedProduct.getAccountClasses())
//            {
//                System.out.println("-------- approvedProduct.getId() = " + accountClass.getId());
//            }
        }


        //now create user in primary database
        //if (!Status.APPROVED.equalsIgnoreCase(request.getWorkflowStatus())) {
        String restUrl = apiGatewayUrl + "/tellerapp/param/api/product/approved/createinprimary";
        Product primaryProduct = new Product();

        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        beanUtils.copyProperties(primaryProduct, approvedProduct);

        if (approvedProduct.getOriginalId() != null) {
            primaryProduct.setId(approvedProduct.getOriginalId()); //this is important to update the right record in primary db
            primaryProduct.setOriginalId(approvedProduct.getId()); //to keep reference to primary record
        }
//        System.out.println("-------Start primaryProduct Json:");
//        System.out.println(new DBPRestTemplateFactory().objectMapper().writeValueAsString(primaryProduct));
//        System.out.println("-------End primaryProduct Json:");

        RestResponse<Product> restResponse = vn.fpt.dbp.vccb.core.rest.product.ProductService.cud(restUrl, primaryProduct);
//        System.out.print("-------Finish vn.fpt.dbp.vccb.core.rest.product.ProductService.cud(restUrl,  primaryProduct)");
        if (restResponse.getStatus() != 0) {
//            System.out.println("-------Eror here-----------------");
            throw new Exception(restResponse.getErrorMessage());
        }
//        System.out.println("-------End Approve");
        return approvedProduct;

    }

    @Transactional(rollbackFor = Exception.class)
    public Product approveInPrimary(Product request) throws Exception{

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception(DBPException.NO_APPROVER);
        }
//        System.out.print("+++Start to run function approveInPrimary");

        Product approvedProduct = updateProductInfo(request, Status.APPROVED);

//        System.out.print("+++End to run function approveInPrimary");

        return approvedProduct;
    }

    @Transactional
    public Product reject(Product request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception(DBPException.NO_APPROVER);
        }

        if (request.getComments() == null || request.getComments().size() <= 0 ){
            throw new Exception("No comment specified");
        }

        return updateProductInfo(request, Status.REJECTED);

    }

    @Transactional
    public Product deleteSaveDraft(Product request) throws Exception {

        if (request.getId() == null) {
            throw new Exception(DBPException.RECORD_NOT_EXISTED);

        }
        Product deletedProduct = productRepository.findOne(request.getId());

        if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedProduct.getWorkflowStatus())) {
            throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);

        }

        productRepository.delete(deletedProduct);
        return new Product(request.getId());
    }

    private Product updateProductInfo(Product request, String status) throws Exception {

//        System.out.print("----Start to run updateProductInfo");
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        Product product = null;

        if(request.getId() != null)
        {
            product = productRepository.findOne(request.getId());
        }

        //remove old data
        if(product != null)
        {

            for (AccountClass accountClass : product.getAccountClasses())
            {
                accountClass.setProduct(null);
            }
            product.getAccountClasses().clear();
            for (ProductAccountClass productAccountClass : product.getProductAccountClasses())
            {
                productAccountClass.setProduct(null);
            }
            product.getProductAccountClasses().clear();
            for (ProductCustomer productCustomer : product.getProductCustomers())
            {
                productCustomer.setProduct(null);
            }
            product.getProductCustomers().clear();
            for (ProductLimit productLimit : product.getProductLimits())
            {
                productLimit.setProduct(null);
            }
            product.getProductLimits().clear();
            for (ProductPromotion productPromotion : product.getProductPromotions())
            {
                productPromotion.setProduct(null);
            }
            product.getProductPromotions().clear();
            for(Comment comment : product.getComments())
            {
                comment.setWorkFlowModel(null);
            }
            product.getComments().clear();

            beanUtils.copyProperties(product, request);

            //beanUtils.copyPropertie không copy những properties có value Null.
            //Nên cần update những trường này trong trường hợp value mới là Null.
            product.setAssignee(request.getAssignee());
            //Khi Save_Draft sẽ có trường hợp save đè bằng value Null cho cả các field required
            if(Status.SAVE_DRAFT.equalsIgnoreCase(product.getWorkflowStatus())){
                product = request;
            }
        }
        else
        {
           product = request;
        }

        if(product.getAccountClasses() != null && product.getAccountClasses().size() > 0)
        {
            for(AccountClass accountClass : product.getAccountClasses())
            {
                accountClass.setProduct(product);
            }
        }

        if(product.getProductAccountClasses() != null && product.getProductAccountClasses().size() > 0)
        {
            for(ProductAccountClass productAccountClass : product.getProductAccountClasses())
            {
                productAccountClass.setProduct(product);
            }
        }

        if(product.getProductCustomers() != null && product.getProductCustomers().size() > 0)
        {
            for(ProductCustomer productCustomer : product.getProductCustomers())
            {
                productCustomer.setProduct(product);
            }
        }

        if(product.getProductLimits() != null && product.getProductLimits().size() > 0)
        {
            for(ProductLimit productLimit : product.getProductLimits())
            {
                productLimit.setProduct(product);
            }
        }

        if(product.getProductPromotions() != null && product.getProductPromotions().size() > 0)
        {
            for(ProductPromotion productPromotion : product.getProductPromotions())
            {
                productPromotion.setProduct(product);
            }
        }

        if(product.getComments() != null && product.getComments().size() > 0)
        {
            for(Comment comment : product.getComments())
            {
                comment.setWorkFlowModel(product);
            }
        }

        product.setWorkflowStatus(status);
//        System.out.print("----End to run updateProductInfo");

        return productRepository.save(product);

    }

    private void swapProduct(Long firstId, Long secondId) throws Exception {
        Product firstProduct, secondProduct;
        Product tempFirstProduct, tempSecondProduct, emptyProduct;
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

//        System.out.println("******** Start:swapProduct *********");

        firstProduct = productRepository.findOne(firstId);
        secondProduct = productRepository.findOne(secondId);

        tempFirstProduct = new Product();
        tempSecondProduct = new Product();
        emptyProduct = new Product();

        if (firstProduct != null && secondProduct != null){

            beanUtils.copyProperties(tempFirstProduct, firstProduct);
            beanUtils.copyProperties(tempSecondProduct, secondProduct);
            emptyProduct.setId(firstId);

            //Xu ly cho firstProduct
            tempFirstProduct.setId(secondId);
            for(AccountClass accountClass : tempFirstProduct.getAccountClasses())
            {
                accountClass.setProduct(tempFirstProduct);
            }

            for(ProductAccountClass productAccountClass : tempFirstProduct.getProductAccountClasses())
            {
                productAccountClass.setProduct(tempFirstProduct);
            }

            for(ProductCustomer productCustomer : tempFirstProduct.getProductCustomers())
            {
                productCustomer.setProduct(tempFirstProduct);
            }

            for(ProductLimit productLimit : tempFirstProduct.getProductLimits())
            {
                productLimit.setProduct(tempFirstProduct);
            }

            for(ProductPromotion productPromotion : tempFirstProduct.getProductPromotions())
            {
                productPromotion.setProduct(tempFirstProduct);
            }

            for(Comment comment : tempFirstProduct.getComments())
            {
                comment.setWorkFlowModel(tempFirstProduct);
            }

            //Xu ly cho secondProduct

            tempSecondProduct.setId(firstId);
            for(AccountClass accountClass : tempSecondProduct.getAccountClasses())
            {
                accountClass.setProduct(tempSecondProduct);
            }

            for(ProductAccountClass productAccountClass : tempSecondProduct.getProductAccountClasses())
            {
                productAccountClass.setProduct(tempSecondProduct);
            }

            for(ProductCustomer productCustomer : tempSecondProduct.getProductCustomers())
            {
                productCustomer.setProduct(tempSecondProduct);
            }

            for(ProductLimit productLimit : tempSecondProduct.getProductLimits())
            {
                productLimit.setProduct(tempSecondProduct);
            }

            for(ProductPromotion productPromotion : tempSecondProduct.getProductPromotions())
            {
                productPromotion.setProduct(tempSecondProduct);
            }

            for(Comment comment : tempSecondProduct.getComments())
            {
                comment.setWorkFlowModel(tempSecondProduct);
            }
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
            productRepository.save(emptyProduct);
            productRepository.save(tempFirstProduct);
            productRepository.save(tempSecondProduct);
        }
    }
}