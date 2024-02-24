
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "errorResponseFlow", targetNamespace = "http://soprabanking.com/amplitude")
public class ErrorResponseFlow_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ErrorResponseFlow faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ErrorResponseFlow_Exception(String message, ErrorResponseFlow faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ErrorResponseFlow_Exception(String message, ErrorResponseFlow faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rawbank.admin.client.stubs.getAccountList.ErrorResponseFlow
     */
    public ErrorResponseFlow getFaultInfo() {
        return faultInfo;
    }

}
