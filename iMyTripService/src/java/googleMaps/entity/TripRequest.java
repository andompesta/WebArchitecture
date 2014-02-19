/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMaps.entity;

import database.entity.Rc;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marco
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement
public class TripRequest extends Rc {
    @XmlElement
    private Boolean Sensor;
    @XmlElement
    private String Origin;
    @XmlElement
    private String Destination;
    @XmlElement
    private String Mode;
    @XmlElement
    private Long DepartureTimeMs;
    @XmlElement
    private Long ArrivalTimeMs;

    public TripRequest()
    {
    }

    public TripRequest(Boolean Sensor, String Origin, String Destination, String Mode, Long DepartureTimeMs, Long ArrivalTimeMs)
    {
        this.Sensor = Sensor;
        this.Origin = Origin;
        this.Destination = Destination;
        this.Mode = Mode;
        this.DepartureTimeMs = DepartureTimeMs;
        this.ArrivalTimeMs = ArrivalTimeMs;
    }

    /**
     * @return the Sensor
     */
    public Boolean getSensor() {
        return Sensor;
    }

    /**
     * @param Sensor the Sensor to set
     */
    public void setSensor(Boolean Sensor) {
        this.Sensor = Sensor;
    }

    /**
     * @return the Origin
     */
    public String getOrigin() {
        return Origin;
    }

    /**
     * @param Origin the Origin to set
     */
    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    /**
     * @return the Destination
     */
    public String getDestination() {
        return Destination;
    }

    /**
     * @param Destination the Destination to set
     */
    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    /**
     * @return the Mode
     */
    public String getMode() {
        return Mode;
    }

    /**
     * @param Mode the Mode to set
     */
    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    /**
     * @return the DepartureTimeMs
     */
    public Long getDepartureTimeMs() {
        return DepartureTimeMs;
    }

    /**
     * @param DepartureTimeMs the DepartureTimeMs to set
     */
    public void setDepartureTimeMs(Long DepartureTimeMs) {
        this.DepartureTimeMs = DepartureTimeMs;
    }

    /**
     * @return the ArrivalTimeMs
     */
    public Long getArrivalTimeMs() {
        return ArrivalTimeMs;
    }

    /**
     * @param ArrivalTimeMs the ArrivalTimeMs to set
     */
    public void setArrivalTimeMs(Long ArrivalTimeMs) {
        this.ArrivalTimeMs = ArrivalTimeMs;
    }
   
}
