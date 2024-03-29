
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "getAccountDetailPortType", targetNamespace = "http://soprabanking.com/amplitude")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GetAccountDetailPortType {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.rawbank.admin.client.stubs.getAccountDetails.GetStatusResponseFlow
     * @throws ErrorResponseFlow_Exception
     */
    @WebMethod(action = "getStatus")
    @WebResult(name = "getStatusResponseFlow", targetNamespace = "http://soprabanking.com/amplitude", partName = "parameters")
    public GetStatusResponseFlow getStatus(
        @WebParam(name = "getStatusRequestFlow", targetNamespace = "http://soprabanking.com/amplitude", partName = "parameters")
        GetStatusRequestFlow parameters)
        throws ErrorResponseFlow_Exception
    ;

    /**
     * 
     * @param parameters
     * @return
     *     returns com.rawbank.admin.client.stubs.getAccountDetails.GetAccountDetailResponseFlow
     * @throws ErrorResponseFlow_Exception
     */
    @WebMethod(action = "getAccountDetail")
    @WebResult(name = "getAccountDetailResponseFlow", targetNamespace = "http://soprabanking.com/amplitude", partName = "parameters")
    public GetAccountDetailResponseFlow getAccountDetail(
        @WebParam(name = "getAccountDetailRequestFlow", targetNamespace = "http://soprabanking.com/amplitude", partName = "parameters")
        GetAccountDetailRequestFlow parameters)
        throws ErrorResponseFlow_Exception
    ;

}
