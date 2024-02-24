
package com.rawbank.admin.client.stubs.getAccountList;

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
@WebServiceClient(name = "getAccountList", targetNamespace = "http://soprabanking.com/amplitude", wsdlLocation = "file:/C:/Users/josuek/eclipse-workspace/adminPortal/src/main/resources/wsdl/getAccountList.wsdl")
public class GetAccountList
    extends Service
{

    private final static URL GETACCOUNTLIST_WSDL_LOCATION;
    private final static WebServiceException GETACCOUNTLIST_EXCEPTION;
    private final static QName GETACCOUNTLIST_QNAME = new QName("http://soprabanking.com/amplitude", "getAccountList");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/josuek/eclipse-workspace/adminPortal/src/main/resources/wsdl/getAccountList.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GETACCOUNTLIST_WSDL_LOCATION = url;
        GETACCOUNTLIST_EXCEPTION = e;
    }

    public GetAccountList() {
        super(__getWsdlLocation(), GETACCOUNTLIST_QNAME);
    }

    public GetAccountList(WebServiceFeature... features) {
        super(__getWsdlLocation(), GETACCOUNTLIST_QNAME, features);
    }

    public GetAccountList(URL wsdlLocation) {
        super(wsdlLocation, GETACCOUNTLIST_QNAME);
    }

    public GetAccountList(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETACCOUNTLIST_QNAME, features);
    }

    public GetAccountList(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetAccountList(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GetAccountListPortType
     */
    @WebEndpoint(name = "getAccountListPortType")
    public GetAccountListPortType getGetAccountListPortType() {
        return super.getPort(new QName("http://soprabanking.com/amplitude", "getAccountListPortType"), GetAccountListPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetAccountListPortType
     */
    @WebEndpoint(name = "getAccountListPortType")
    public GetAccountListPortType getGetAccountListPortType(WebServiceFeature... features) {
        return super.getPort(new QName("http://soprabanking.com/amplitude", "getAccountListPortType"), GetAccountListPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GETACCOUNTLIST_EXCEPTION!= null) {
            throw GETACCOUNTLIST_EXCEPTION;
        }
        return GETACCOUNTLIST_WSDL_LOCATION;
    }

}