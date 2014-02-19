/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iMyTripJsonInterface;

import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import servicesoap.accountservice.Account;

/**
 *
 * @author tlmarco
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement
public class bonificoParam
{
    private UUID uuid ;
    private int idContoCorrente;
    private float prezzo;
    private String descrizione;

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public int getIdContoCorrente()
    {
        return idContoCorrente;
    }

    public void setIdContoCorrente(int idContoCorrente)
    {
        this.idContoCorrente = idContoCorrente;
    }

    public float getPrezzo()
    {
        return prezzo;
    }

    public void setPrezzo(float prezzo)
    {
        this.prezzo = prezzo;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
    
}
