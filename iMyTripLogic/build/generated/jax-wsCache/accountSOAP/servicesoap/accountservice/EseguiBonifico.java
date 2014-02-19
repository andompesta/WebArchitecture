
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eseguiBonifico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eseguiBonifico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idConto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prezzo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiBonifico", propOrder = {
    "userName",
    "idConto",
    "prezzo",
    "descrizione"
})
public class EseguiBonifico {

    protected String userName;
    protected int idConto;
    protected float prezzo;
    protected String descrizione;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the idConto property.
     * 
     */
    public int getIdConto() {
        return idConto;
    }

    /**
     * Sets the value of the idConto property.
     * 
     */
    public void setIdConto(int value) {
        this.idConto = value;
    }

    /**
     * Gets the value of the prezzo property.
     * 
     */
    public float getPrezzo() {
        return prezzo;
    }

    /**
     * Sets the value of the prezzo property.
     * 
     */
    public void setPrezzo(float value) {
        this.prezzo = value;
    }

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

}
