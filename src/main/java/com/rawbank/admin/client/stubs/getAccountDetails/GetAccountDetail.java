
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "getAccountDetail", targetNamespace = "http://soprabanking.com/amplitude", wsdlLocation = "file:/C:/Users/josuek/eclipse-workspace/adminPortal/src/main/resources/wsdl/getAccountDetail.wsdl")
public class GetAccountDetail
    extends Service
{

    private final static URL GETACCOUNTDETAIL_WSDL_LOCATION;
    private final static WebServiceException GETACCOUNTDETAIL_EXCEPTION;
    private final static QName GETACCOUNTDETAIL_QNAME = new QName("http://soprabanking.com/amplitude", "getAccountDetail");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/josuek/eclipse-workspace/adminPortal/src/main/resources/wsdl/getAccountDetail.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GETACCOUNTDETAIL_WSDL_LOCATION = url;
        GETACCOUNTDETAIL_EXCEPTION = e;
    }

    public GetAccountDetail() {
        super(__getWsdlLocation(), GETACCOUNTDETAIL_QNAME);
    }

    public GetAccountDetail(WebServiceFeature... features) {
        super(__getWsdlLocation(), GETACCOUNTDETAIL_QNAME, features);
    }

    public GetAccountDetail(URL wsdlLocation) {
        super(wsdlLocation, GETACCOUNTDETAIL_QNAME);
    }

    public GetAccountDetail(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETACCOUNTDETAIL_QNAME, features);
    }

    public GetAccountDetail(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetAccountDetail(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GetAccountDetailPortType
     */
    @WebEndpoint(name = "getAccountDetailPortType")
    public GetAccountDetailPortType getGetAccountDetailPortType() {
        return super.getPort(new QName("http://soprabanking.com/amplitude", "getAccountDetailPortType"), GetAccountDetailPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetAccountDetailPortType
     */
    @WebEndpoint(name = "getAccountDetailPortType")
    public GetAccountDetailPortType getGetAccountDetailPortType(WebServiceFeature... features) {
        return super.getPort(new QName("http://soprabanking.com/amplitude", "getAccountDetailPortType"), GetAccountDetailPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GETACCOUNTDETAIL_EXCEPTION!= null) {
            throw GETACCOUNTDETAIL_EXCEPTION;
        }
        return GETACCOUNTDETAIL_WSDL_LOCATION;
    }

}
