
package servicesoap.googlemapservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicesoap.googlemapservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPrezzoTariffeExtraUrbaneResponse_QNAME = new QName("http://googleMapService.serviceSoap/", "getPrezzoTariffeExtraUrbaneResponse");
    private final static QName _IMyTripJsonResponse_QNAME = new QName("http://googleMapService.serviceSoap/", "iMyTripJsonResponse");
    private final static QName _TripRequest_QNAME = new QName("http://googleMapService.serviceSoap/", "tripRequest");
    private final static QName _GetJsonSOAPResponse_QNAME = new QName("http://googleMapService.serviceSoap/", "getJsonSOAPResponse");
    private final static QName _Rc_QNAME = new QName("http://googleMapService.serviceSoap/", "rc");
    private final static QName _GetJsonSOAP_QNAME = new QName("http://googleMapService.serviceSoap/", "getJsonSOAP");
    private final static QName _IMyTripJson_QNAME = new QName("http://googleMapService.serviceSoap/", "iMyTripJson");
    private final static QName _GetPrezzoTariffeExtraUrbane_QNAME = new QName("http://googleMapService.serviceSoap/", "getPrezzoTariffeExtraUrbane");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicesoap.googlemapservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetJsonSOAP }
     * 
     */
    public GetJsonSOAP createGetJsonSOAP() {
        return new GetJsonSOAP();
    }

    /**
     * Create an instance of {@link IMyTripJson }
     * 
     */
    public IMyTripJson createIMyTripJson() {
        return new IMyTripJson();
    }

    /**
     * Create an instance of {@link GetPrezzoTariffeExtraUrbane }
     * 
     */
    public GetPrezzoTariffeExtraUrbane createGetPrezzoTariffeExtraUrbane() {
        return new GetPrezzoTariffeExtraUrbane();
    }

    /**
     * Create an instance of {@link GetPrezzoTariffeExtraUrbaneResponse }
     * 
     */
    public GetPrezzoTariffeExtraUrbaneResponse createGetPrezzoTariffeExtraUrbaneResponse() {
        return new GetPrezzoTariffeExtraUrbaneResponse();
    }

    /**
     * Create an instance of {@link IMyTripJsonResponse }
     * 
     */
    public IMyTripJsonResponse createIMyTripJsonResponse() {
        return new IMyTripJsonResponse();
    }

    /**
     * Create an instance of {@link TripRequest }
     * 
     */
    public TripRequest createTripRequest() {
        return new TripRequest();
    }

    /**
     * Create an instance of {@link Rc }
     * 
     */
    public Rc createRc() {
        return new Rc();
    }

    /**
     * Create an instance of {@link GetJsonSOAPResponse }
     * 
     */
    public GetJsonSOAPResponse createGetJsonSOAPResponse() {
        return new GetJsonSOAPResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrezzoTariffeExtraUrbaneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "getPrezzoTariffeExtraUrbaneResponse")
    public JAXBElement<GetPrezzoTariffeExtraUrbaneResponse> createGetPrezzoTariffeExtraUrbaneResponse(GetPrezzoTariffeExtraUrbaneResponse value) {
        return new JAXBElement<GetPrezzoTariffeExtraUrbaneResponse>(_GetPrezzoTariffeExtraUrbaneResponse_QNAME, GetPrezzoTariffeExtraUrbaneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IMyTripJsonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "iMyTripJsonResponse")
    public JAXBElement<IMyTripJsonResponse> createIMyTripJsonResponse(IMyTripJsonResponse value) {
        return new JAXBElement<IMyTripJsonResponse>(_IMyTripJsonResponse_QNAME, IMyTripJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TripRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "tripRequest")
    public JAXBElement<TripRequest> createTripRequest(TripRequest value) {
        return new JAXBElement<TripRequest>(_TripRequest_QNAME, TripRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJsonSOAPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "getJsonSOAPResponse")
    public JAXBElement<GetJsonSOAPResponse> createGetJsonSOAPResponse(GetJsonSOAPResponse value) {
        return new JAXBElement<GetJsonSOAPResponse>(_GetJsonSOAPResponse_QNAME, GetJsonSOAPResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "rc")
    public JAXBElement<Rc> createRc(Rc value) {
        return new JAXBElement<Rc>(_Rc_QNAME, Rc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJsonSOAP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "getJsonSOAP")
    public JAXBElement<GetJsonSOAP> createGetJsonSOAP(GetJsonSOAP value) {
        return new JAXBElement<GetJsonSOAP>(_GetJsonSOAP_QNAME, GetJsonSOAP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IMyTripJson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "iMyTripJson")
    public JAXBElement<IMyTripJson> createIMyTripJson(IMyTripJson value) {
        return new JAXBElement<IMyTripJson>(_IMyTripJson_QNAME, IMyTripJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrezzoTariffeExtraUrbane }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://googleMapService.serviceSoap/", name = "getPrezzoTariffeExtraUrbane")
    public JAXBElement<GetPrezzoTariffeExtraUrbane> createGetPrezzoTariffeExtraUrbane(GetPrezzoTariffeExtraUrbane value) {
        return new JAXBElement<GetPrezzoTariffeExtraUrbane>(_GetPrezzoTariffeExtraUrbane_QNAME, GetPrezzoTariffeExtraUrbane.class, null, value);
    }

}
