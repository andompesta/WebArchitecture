
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceCarta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idConto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "conto", propOrder = {
    "codiceCarta",
    "idConto",
    "saldo",
    "tipo",
    "userName"
})
public class Conto {

    protected String codiceCarta;
    protected Integer idConto;
    protected float saldo;
    protected String tipo;
    protected Account userName;

    /**
     * Gets the value of the codiceCarta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCarta() {
        return codiceCarta;
    }

    /**
     * Sets the value of the codiceCarta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCarta(String value) {
        this.codiceCarta = value;
    }

    /**
     * Gets the value of the idConto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdConto() {
        return idConto;
    }

    /**
     * Sets the value of the idConto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdConto(Integer value) {
        this.idConto = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     */
    public void setSaldo(float value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
