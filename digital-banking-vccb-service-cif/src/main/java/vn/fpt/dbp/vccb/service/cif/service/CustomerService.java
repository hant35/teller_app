package vn.fpt.dbp.vccb.service.cif.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.customer.*;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerRepository;
import vn.fpt.dbp.vccb.core.domain.organization.Address;
import vn.fpt.dbp.vccb.core.domain.organization.repository.AddressRepository;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class CustomerService {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public Customer saveAsDraft(Customer request) throws Exception {
        Customer customer = request.getId() == null ? null : customerRepository.findOne(request.getId());

        if (customer != null) {

            if (!Status.SAVE_DRAFT.equalsIgnoreCase(customer.getWorkflowStatus())) {
                throw new Exception("Record is not savedraft");
            }
        }

        return updateCustomerInfo(request, Status.SAVE_DRAFT);
    }

    @Transactional
    public Customer sendForApprove(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        return updateCustomerInfo(request, Status.SEND_FOR_APPROVE );
    }

    @Transactional
    public Customer assign(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getAssignee() == null || request.getAssignee().getId() == null){
            throw new Exception("No assignee specified");
        }

        return updateCustomerInfo(request, Status.ASSIGNED);
    }

    @Transactional
    public Customer returnAssign(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getAssignee() == null || request.getAssignee().getId() == null){
            throw new Exception("No assignee specified");
        }

        request.setAssignee(null);

        return updateCustomerInfo(request, Status.SEND_FOR_APPROVE);
    }

    @Transactional
    public Customer approveInPrimary(Customer request) throws Exception{

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception("No approver specified");
        }

        Customer approvedCustomer = updateCustomerInfo(request, Status.APPROVED);

        return approvedCustomer;
    }

    @Transactional
    public Customer reject(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
            throw new Exception("No approver specified");
        }

        if (request.getComments() == null || request.getComments().size() <= 0 ){
            throw new Exception("No comment specified");
        }

        return updateCustomerInfo(request, Status.REJECTED);

    }

    @Transactional
    public Customer deleteSaveDraft(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");

        }
        Customer deletedCustomer = customerRepository.findOne(request.getId());

        if (!Status.SAVE_DRAFT.equalsIgnoreCase(deletedCustomer.getWorkflowStatus())) {
            throw new Exception("Record is not savedraft");

        }

        customerRepository.delete(deletedCustomer);
        return new Customer(request.getId());
    }

    @Transactional
    public Customer approve(Customer request) throws Exception {

        if (request.getId() == null) {
            throw new Exception("Record does not exist");
        }

        if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null) {
            throw new Exception("No approver specified");
        }

        Customer originalCustomer = customerRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(request.getCode(), Status.APPROVED);

        if(request.getOriginalId() == null){
			request.setOriginalId(request.getId());
		}
        Customer approvedCustomer = updateCustomerInfo(request, Status.APPROVED);

        if(originalCustomer != null && approvedCustomer != null ) {
            Long newId = approvedCustomer.getId();
            Long originalId = originalCustomer.getId();

            swapCustomer(newId, originalId);
            //Sau khi swap thi can Get lai approveCustomer voi Id của originalId
            approvedCustomer = customerRepository.findOne(originalId);
        }


        //now create user in primary database
        //if (!Status.APPROVED.equalsIgnoreCase(request.getWorkflowStatus())) {
        String restUrl = apiGatewayUrl + "/tellerapp/cif/api/customer/approved/createinprimary";
        Customer primaryCustomer = new Customer();

        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        beanUtils.copyProperties(primaryCustomer, approvedCustomer);

        if (approvedCustomer.getOriginalId() != null) {
            primaryCustomer.setId(approvedCustomer.getOriginalId()); //this is important to update the right record in primary db
            primaryCustomer.setOriginalId(approvedCustomer.getId()); //to keep reference to primary record
        }
        RestResponse<Customer> restResponse = vn.fpt.dbp.vccb.core.rest.customer.CustomerService.cud(restUrl, primaryCustomer);
        if (restResponse.getStatus() != 0) {
            throw new Exception(restResponse.getErrorMessage());
        }
        return approvedCustomer;
    }

    private Customer updateCustomerInfo(Customer request, String status) throws Exception{
//        System.out.print("----Start to run updateCustomerInfo");
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
        Customer customer = null;

        if(request.getId() != null)
        {
            customer = customerRepository.findOne(request.getId());
        }

        //remove old data
        if(customer != null)
        {

            for (CustomerFile customerFile: customer.getCustomerFiles())
            {
                customerFile.setCustomer(null);
            }
            customer.getCustomerFiles().clear();

            for (CustomerImage customerImage: customer.getCustomerImages())
            {
                customerImage.setCustomer(null);
            }
            customer.getCustomerImages().clear();

            for (CustomerManager customerManager: customer.getCustomerManagers())
            {
                customerManager.setCustomer(null);
            }
            customer.getCustomerManagers().clear();

            for (CustomerPaymentInfo customerPaymentInfo: customer.getCustomerPaymentInfos())
            {
                customerPaymentInfo.setCustomer(null);
            }
            customer.getCustomerPaymentInfos().clear();

//          List customer liên quan
            for (CustomerRefCustomer customerRefCustomer: customer.getCustomerRefCustomers())
            {
                customerRefCustomer.setCustomerFrom(null);
            }
            customer.getCustomerFiles().clear();

            for (CustomerRefPerson customerRefPerson: customer.getCustomerRefPersons())
            {
                customerRefPerson.setCustomer(null);
            }
            customer.getCustomerRefPersons().clear();


            for(Comment comment : customer.getComments())
            {
                comment.setWorkFlowModel(null);
            }
            customer.getComments().clear();

            beanUtils.copyProperties(customer, request);

            //beanUtils.copyPropertie không copy những properties có value Null.
            //Nên cần update những trường này trong trường hợp value mới là Null.
            customer.setAssignee(request.getAssignee());
            //Khi Save_Draft sẽ có trường hợp save đè bằng value Null cho cả các field required
            if(Status.SAVE_DRAFT.equalsIgnoreCase(customer.getWorkflowStatus())){
                customer = request;
            }
        }
        else
        {
            customer = request;
        }

        if(customer.getCustomerFiles() != null && customer.getCustomerFiles().size() > 0)
        {
            for(CustomerFile customerFile : customer.getCustomerFiles())
            {
                customerFile.setCustomer(customer);
            }
        }

        if(customer.getCustomerImages() != null && customer.getCustomerImages().size() > 0)
        {
            for(CustomerImage customerImage : customer.getCustomerImages())
            {
                customerImage.setCustomer(customer);
            }
        }
        
        if(customer.getCustomerManagers() != null && customer.getCustomerManagers().size() > 0)
        {
            for(CustomerManager customerManager : customer.getCustomerManagers())
            {
                customerManager.setCustomer(customer);
            }
        }

        if(customer.getCustomerPaymentInfos() != null && customer.getCustomerPaymentInfos().size() > 0)
        {
            for(CustomerPaymentInfo customerPaymentInfo : customer.getCustomerPaymentInfos())
            {
                customerPaymentInfo.setCustomer(customer);
            }
        }

//      List customer liên quan
        if(customer.getCustomerRefCustomers() != null && customer.getCustomerRefCustomers().size() > 0)
        {
            for(CustomerRefCustomer customerRefCustomer : customer.getCustomerRefCustomers())
            {
                customerRefCustomer.setCustomerFrom(customer);
            }
        }

        if(customer.getCustomerRefPersons() != null && customer.getCustomerRefPersons().size() > 0)
        {
            for(CustomerRefPerson customerRefPerson : customer.getCustomerRefPersons())
            {
                customerRefPerson.setCustomer(customer);
            }
        }

        if(customer.getComments() != null && customer.getComments().size() > 0)
        {
            for(Comment comment : customer.getComments())
            {
                comment.setWorkFlowModel(customer);
            }
        }

        //Customer - Address đang là n - 1
        updateAddressRepository(customer);

        customer.setWorkflowStatus(status);
//        System.out.print("----End to run updateCustomerInfo");

        return customerRepository.save(customer);

    }

    private void swapCustomer(Long firstId, Long secondId) throws Exception {
        Customer firstCustomer, secondCustomer;
        Customer tempFirstCustomer, tempSecondCustomer, emptyCustomer;
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

//        System.out.println("******** Start:swapCustomer *********");

        firstCustomer = customerRepository.findOne(firstId);
        secondCustomer = customerRepository.findOne(secondId);

        tempFirstCustomer = new Customer();
        tempSecondCustomer = new Customer();
        emptyCustomer = new Customer();

        if (firstCustomer != null && secondCustomer != null){

            beanUtils.copyProperties(tempFirstCustomer, firstCustomer);
            beanUtils.copyProperties(tempSecondCustomer, secondCustomer);
            emptyCustomer.setId(firstId);

            //Xu ly cho firstCustomer
            tempFirstCustomer.setId(secondId);

            for(CustomerFile customerFile : tempFirstCustomer.getCustomerFiles())
            {
                customerFile.setCustomer(tempFirstCustomer);
            }

            for(CustomerImage customerImage : tempFirstCustomer.getCustomerImages())
            {
                customerImage.setCustomer(tempFirstCustomer);
            }

            for(CustomerManager customerManager : tempFirstCustomer.getCustomerManagers())
            {
                customerManager.setCustomer(tempFirstCustomer);
            }

            for(CustomerPaymentInfo customerPaymentInfo : tempFirstCustomer.getCustomerPaymentInfos())
            {
                customerPaymentInfo.setCustomer(tempFirstCustomer);
            }

//          List customer liên quan
            for(CustomerRefCustomer customerRefCustomer : tempFirstCustomer.getCustomerRefCustomers())
            {
                customerRefCustomer.setCustomerFrom(tempFirstCustomer);
            }

            for(CustomerRefPerson customerRefPerson : tempFirstCustomer.getCustomerRefPersons())
            {
                customerRefPerson.setCustomer(tempFirstCustomer);
            }

            for(Comment comment : tempFirstCustomer.getComments())
            {
                comment.setWorkFlowModel(tempFirstCustomer);
            }

            //Xu ly cho secondCustomer
            tempSecondCustomer.setId(firstId);

            for(CustomerFile customerFile : tempSecondCustomer.getCustomerFiles())
            {
                customerFile.setCustomer(tempSecondCustomer);
            }

            for(CustomerImage customerImage : tempSecondCustomer.getCustomerImages())
            {
                customerImage.setCustomer(tempSecondCustomer);
            }

            for(CustomerManager customerManager : tempSecondCustomer.getCustomerManagers())
            {
                customerManager.setCustomer(tempSecondCustomer);
            }

            for(CustomerPaymentInfo customerPaymentInfo : tempSecondCustomer.getCustomerPaymentInfos())
            {
                customerPaymentInfo.setCustomer(tempSecondCustomer);
            }

//          List customer liên quan
            for(CustomerRefCustomer customerRefCustomer : tempSecondCustomer.getCustomerRefCustomers())
            {
                customerRefCustomer.setCustomerFrom(tempSecondCustomer);
            }

            for(CustomerRefPerson customerRefPerson : tempSecondCustomer.getCustomerRefPersons())
            {
                customerRefPerson.setCustomer(tempSecondCustomer);
            }

            for(Comment comment : tempSecondCustomer.getComments())
            {
                comment.setWorkFlowModel(tempSecondCustomer);
            }
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
            customerRepository.save(emptyCustomer);
            customerRepository.save(tempFirstCustomer);
            customerRepository.save(tempSecondCustomer);
        }
    }

    public User getUser(String username){

        if(StringUtils.isEmpty(username)){
            return null;
        }

        return userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);
    }

    private void updateAddressRepository(Customer request){
        Address addressRequest;
        Address addressResponse;

        if(request.getAddress() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getAddress().getCity());
            addressRequest.setDistrict(request.getAddress().getDistrict());
            addressRequest.setWard(request.getAddress().getWard());
            addressRequest.setStreet(request.getAddress().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setAddress(addressResponse);
        }

        if(request.getAddressNationalResident() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getAddressNationalResident().getCity());
            addressRequest.setDistrict(request.getAddressNationalResident().getDistrict());
            addressRequest.setWard(request.getAddressNationalResident().getWard());
            addressRequest.setStreet(request.getAddressNationalResident().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setAddressNationalResident(addressResponse);
        }

        if(request.getAddressNationalResident2() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getAddressNationalResident2().getCity());
            addressRequest.setDistrict(request.getAddressNationalResident2().getDistrict());
            addressRequest.setWard(request.getAddressNationalResident2().getWard());
            addressRequest.setStreet(request.getAddressNationalResident2().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setAddressNationalResident2(addressResponse);
        }

        if(request.getAddressTrading() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getAddressTrading().getCity());
            addressRequest.setDistrict(request.getAddressTrading().getDistrict());
            addressRequest.setWard(request.getAddressTrading().getWard());
            addressRequest.setStreet(request.getAddressTrading().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setAddressTrading(addressResponse);
        }

        if(request.getAddressVnResident() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getAddressVnResident().getCity());
            addressRequest.setDistrict(request.getAddressVnResident().getDistrict());
            addressRequest.setWard(request.getAddressVnResident().getWard());
            addressRequest.setStreet(request.getAddressVnResident().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setAddressVnResident(addressResponse);
        }

        if(request.getCompanyAddress() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getCompanyAddress().getCity());
            addressRequest.setDistrict(request.getCompanyAddress().getDistrict());
            addressRequest.setWard(request.getCompanyAddress().getWard());
            addressRequest.setStreet(request.getCompanyAddress().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setCompanyAddress(addressResponse);
        }

        if(request.getWorkCompanyAddress() != null){
            addressRequest = new Address();
            addressRequest.setCity(request.getWorkCompanyAddress().getCity());
            addressRequest.setDistrict(request.getWorkCompanyAddress().getDistrict());
            addressRequest.setWard(request.getWorkCompanyAddress().getWard());
            addressRequest.setStreet(request.getWorkCompanyAddress().getStreet());

            addressResponse = addressRepository.save(addressRequest);
            request.setWorkCompanyAddress(addressResponse);
        }
    }
}
