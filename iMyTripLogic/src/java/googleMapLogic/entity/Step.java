/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMapLogic.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import servicesoap.googlemapservice.Rc;

/**
 *
 * @author tlmarco
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement
public class Step extends Rc
{
    @XmlElement
    private String distance;
    @XmlElement
    private String duration;
    @XmlElement
    private String travelMode;
    @XmlElement
    private String line;
    @XmlElement
    private String agency;

    public Step(String distance, String duration, String travelMode, String line, String agency)
    {
        this.distance = distance;
        this.duration = duration;
        this.travelMode = travelMode;
        this.line = line;
        this.agency = agency;
    }

    public Step()
    {
    }

    public String getDistance()
    {
        return distance;
    }

    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public String getTravelMode()
    {
        return travelMode;
    }

    public void setTravelMode(String travelMode)
    {
        this.travelMode = travelMode;
    }

    public String getLine()
    {
        return line;
    }

    public void setLine(String line)
    {
        this.line = line;
    }

    public String getAgency()
    {
        return agency;
    }

    public void setAgency(String agency)
    {
        this.agency = agency;
    }
    
}
