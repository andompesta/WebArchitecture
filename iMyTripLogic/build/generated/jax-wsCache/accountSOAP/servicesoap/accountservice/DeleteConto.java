
package servicesoap.accountservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteConto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteConto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contoToDelete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteConto", propOrder = {
    "contoToDelete"
})
public class DeleteConto {

    protected int contoToDelete;

    /**
     * Gets the value of the contoToDelete property.
     * 
     */
    public int getContoToDelete() {
        return contoToDelete;
    }

    /**
     * Sets the value of the contoToDelete property.
     * 
     */
    public void setContoToDelete(int value) {
        this.contoToDelete = value;
    }

}
