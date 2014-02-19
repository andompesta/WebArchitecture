/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMapLogic.entity;

import java.util.ArrayList;
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
public class Ticket extends Rc
{
    @XmlElement
    private ArrayList<String> nomeBus = new ArrayList<String>();
    @XmlElement
    private float prezzo;

    public Ticket(ArrayList<String> nomeBus, float prezzo)
    {
        this.nomeBus = nomeBus;
        this.prezzo = prezzo;
    }

    public Ticket()
    {
    }

    public ArrayList<String> getNomeBus()
    {
        return nomeBus;
    }

    public void setNomeBus(ArrayList<String> nomeBus)
    {
        this.nomeBus = nomeBus;
    }

    public float getPrezzo()
    {
        return prezzo;
    }

    public void setPrezzo(float prezzo)
    {
        this.prezzo = prezzo;
    }
    
    
}
