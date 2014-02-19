
package servicesoap.googlemapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for iMyTripJson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="iMyTripJson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GoogleRequest" type="{http://googleMapService.serviceSoap/}tripRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iMyTripJson", propOrder = {
    "googleRequest"
})
public class IMyTripJson {

    @XmlElement(name = "GoogleRequest")
    protected TripRequest googleRequest;

    /**
     * Gets the value of the googleRequest property.
     * 
     * @return
     *     possible object is
     *     {@link TripRequest }
     *     
     */
    public TripRequest getGoogleRequest() {
        return googleRequest;
    }

    /**
     * Sets the value of the googleRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link TripRequest }
     *     
     */
    public void setGoogleRequest(TripRequest value) {
        this.googleRequest = value;
    }

}
