package com.alsb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-07-07T23:26:35.745+07:00
 * Generated source version: 3.1.11
 * 
 */
@WebServiceClient(name = "WebService", 
                  wsdlLocation = "file:/D:/BANK_VCCB_TELLER_APP_2016/WIP/03.Program/JavaSource/trunk/vccb-osb/NonFinance.wsdl",
                  targetNamespace = "http://www.alsb.com/") 
public class WebService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.alsb.com/", "WebService");
    public final static QName WebService = new QName("http://www.alsb.com/", "WebService");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/BANK_VCCB_TELLER_APP_2016/WIP/03.Program/JavaSource/trunk/vccb-osb/NonFinance.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WebService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/BANK_VCCB_TELLER_APP_2016/WIP/03.Program/JavaSource/trunk/vccb-osb/NonFinance.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WebService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public WebService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public WebService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public WebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns WebService
     */
    @WebEndpoint(name = "WebService")
    public WebService getWebService() {
        return super.getPort(WebService, WebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebService
     */
    @WebEndpoint(name = "WebService")
    public WebService getWebService(WebServiceFeature... features) {
        return super.getPort(WebService, WebService.class, features);
    }

}
