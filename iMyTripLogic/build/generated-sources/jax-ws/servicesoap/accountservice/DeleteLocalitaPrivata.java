
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteLocalitaPrivata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteLocalitaPrivata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="localitaToDelete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteLocalitaPrivata", propOrder = {
    "localitaToDelete"
})
public class DeleteLocalitaPrivata {

    protected int localitaToDelete;

    /**
     * Gets the value of the localitaToDelete property.
     * 
     */
    public int getLocalitaToDelete() {
        return localitaToDelete;
    }

    /**
     * Sets the value of the localitaToDelete property.
     * 
     */
    public void setLocalitaToDelete(int value) {
        this.localitaToDelete = value;
    }

}
