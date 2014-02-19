
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for localitaPrivata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="localitaPrivata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idLocalita" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latitudine" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="longitudine" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="userName" type="{http://accountService.serviceSoap/}account" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "localitaPrivata", propOrder = {
    "descrizione",
    "idLocalita",
    "latitudine",
    "longitudine",
    "userName"
})
public class LocalitaPrivata {

    protected String descrizione;
    protected Integer idLocalita;
    protected double latitudine;
    protected double longitudine;
    protected Account userName;

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

    /**
     * Gets the value of the idLocalita property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdLocalita() {
        return idLocalita;
    }

    /**
     * Sets the value of the idLocalita property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdLocalita(Integer value) {
        this.idLocalita = value;
    }

    /**
     * Gets the value of the latitudine property.
     * 
     */
    public double getLatitudine() {
        return latitudine;
    }

    /**
     * Sets the value of the latitudine property.
     * 
     */
    public void setLatitudine(double value) {
        this.latitudine = value;
    }

    /**
     * Gets the value of the longitudine property.
     * 
     */
    public double getLongitudine() {
        return longitudine;
    }

    /**
     * Sets the value of the longitudine property.
     * 
     */
    public void setLongitudine(double value) {
        this.longitudine = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link Account }
     *     
     */
    public Account getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Account }
     *     
     */
    public void setUserName(Account value) {
        this.userName = value;
    }

}
