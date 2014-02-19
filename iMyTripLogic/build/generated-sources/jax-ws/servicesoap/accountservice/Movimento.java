
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for movimento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="movimento">
 *   &lt;complexContent>
 *     &lt;extension base="{http://accountService.serviceSoap/}rc">
 *       &lt;sequence>
 *         &lt;element name="idMovimento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idConto" type="{http://accountService.serviceSoap/}conto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movimento", propOrder = {
    "idMovimento",
    "data",
    "importo",
    "descrizione",
    "idConto"
})
public class Movimento
    extends Rc
{

    protected Integer idMovimento;
    protected String data;
    protected float importo;
    protected String descrizione;
    protected Conto idConto;

    /**
     * Gets the value of the idMovimento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdMovimento() {
        return idMovimento;
    }

    /**
     * Sets the value of the idMovimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdMovimento(Integer value) {
        this.idMovimento = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

    /**
     * Gets the value of the importo property.
     * 
     */
    public float getImporto() {
        return importo;
    }

    /**
     * Sets the value of the importo property.
     * 
     */
    public void setImporto(float value) {
        this.importo = value;
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

    /**
     * Gets the value of the idConto property.
     * 
     * @return
     *     possible object is
     *     {@link Conto }
     *     
     */
    public Conto getIdConto() {
        return idConto;
    }

    /**
     * Sets the value of the idConto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Conto }
     *     
     */
    public void setIdConto(Conto value) {
        this.idConto = value;
    }

}
