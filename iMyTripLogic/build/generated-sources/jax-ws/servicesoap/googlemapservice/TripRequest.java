
package servicesoap.googlemapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tripRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tripRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://googleMapService.serviceSoap/}rc">
 *       &lt;sequence>
 *         &lt;element name="Sensor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Origin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepartureTimeMs" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ArrivalTimeMs" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tripRequest", propOrder = {
    "sensor",
    "origin",
    "destination",
    "mode",
    "departureTimeMs",
    "arrivalTimeMs"
})
public class TripRequest
    extends Rc
{

    @XmlElement(name = "Sensor")
    protected Boolean sensor;
    @XmlElement(name = "Origin")
    protected String origin;
    @XmlElement(name = "Destination")
    protected String destination;
    @XmlElement(name = "Mode")
    protected String mode;
    @XmlElement(name = "DepartureTimeMs")
    protected Long departureTimeMs;
    @XmlElement(name = "ArrivalTimeMs")
    protected Long arrivalTimeMs;

    /**
     * Gets the value of the sensor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSensor() {
        return sensor;
    }

    /**
     * Sets the value of the sensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSensor(Boolean value) {
        this.sensor = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the departureTimeMs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDepartureTimeMs() {
        return departureTimeMs;
    }

    /**
     * Sets the value of the departureTimeMs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDepartureTimeMs(Long value) {
        this.departureTimeMs = value;
    }

    /**
     * Gets the value of the arrivalTimeMs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getArrivalTimeMs() {
        return arrivalTimeMs;
    }

    /**
     * Sets the value of the arrivalTimeMs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setArrivalTimeMs(Long value) {
        this.arrivalTimeMs = value;
    }

}
