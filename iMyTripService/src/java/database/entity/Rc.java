/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * @author tlmarco
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement
public class Rc
{
    @XmlElement
    int rc;
    @XmlElement
    UUID uuid;
    @XmlElement
    String description;

    public Rc()
    {
    }

    public Rc(int rc, String description,UUID uuid)
    {
        this.rc = rc;
        this.uuid = uuid;
        this.description = description;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }
    
    public int getRc()
    {
        return rc;
    }

    public void setRc(int rc)
    {
        this.rc = rc;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
