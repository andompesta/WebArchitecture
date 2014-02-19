
package servicesoap.accountservice;

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
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "accountSOAP", targetNamespace = "http://accountService.serviceSoap/", wsdlLocation = "http://localhost:8080/iMyTripService/accountSOAP?wsdl")
public class AccountSOAP_Service
    extends Service
{

    private final static URL ACCOUNTSOAP_WSDL_LOCATION;
    private final static WebServiceException ACCOUNTSOAP_EXCEPTION;
    private final static QName ACCOUNTSOAP_QNAME = new QName("http://accountService.serviceSoap/", "accountSOAP");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/iMyTripService/accountSOAP?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ACCOUNTSOAP_WSDL_LOCATION = url;
        ACCOUNTSOAP_EXCEPTION = e;
    }

    public AccountSOAP_Service() {
        super(__getWsdlLocation(), ACCOUNTSOAP_QNAME);
    }

    public AccountSOAP_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ACCOUNTSOAP_QNAME, features);
    }

    public AccountSOAP_Service(URL wsdlLocation) {
        super(wsdlLocation, ACCOUNTSOAP_QNAME);
    }

    public AccountSOAP_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ACCOUNTSOAP_QNAME, features);
    }

    public AccountSOAP_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AccountSOAP_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AccountSOAP
     */
    @WebEndpoint(name = "accountSOAPPort")
    public AccountSOAP getAccountSOAPPort() {
        return super.getPort(new QName("http://accountService.serviceSoap/", "accountSOAPPort"), AccountSOAP.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AccountSOAP
     */
    @WebEndpoint(name = "accountSOAPPort")
    public AccountSOAP getAccountSOAPPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://accountService.serviceSoap/", "accountSOAPPort"), AccountSOAP.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ACCOUNTSOAP_EXCEPTION!= null) {
            throw ACCOUNTSOAP_EXCEPTION;
        }
        return ACCOUNTSOAP_WSDL_LOCATION;
    }

}