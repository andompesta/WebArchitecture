/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tlmarco
 */
@Entity
@Table(name = "CostoTariffaExtraUrbano")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "CostoTariffaExtraUrbano.findAll", query = "SELECT c FROM CostoTariffaExtraUrbano c"),
    @NamedQuery(name = "CostoTariffaExtraUrbano.findByIdTariffaExtra", query = "SELECT c FROM CostoTariffaExtraUrbano c WHERE c.idTariffaExtra = :idTariffaExtra"),
    @NamedQuery(name = "CostoTariffaExtraUrbano.findByKm", query = "SELECT c FROM CostoTariffaExtraUrbano c WHERE c.km = :km"),
    @NamedQuery(name = "CostoTariffaExtraUrbano.findByPrezzo", query = "SELECT c FROM CostoTariffaExtraUrbano c WHERE c.prezzo = :prezzo"),
    @NamedQuery(name = "CostoTariffaExtraUrbano.findPrezzoForKm", query = "SELECT Max(c.prezzo) FROM CostoTariffaExtraUrbano c WHERE c.km <= :km")
})
public class CostoTariffaExtraUrbano extends Rc implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTariffaExtra")
    private Integer idTariffaExtra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Km")
    private int km;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prezzo")
    private float prezzo;

    public CostoTariffaExtraUrbano()
    {
    }

    public CostoTariffaExtraUrbano(Integer idTariffaExtra)
    {
        this.idTariffaExtra = idTariffaExtra;
    }

    public CostoTariffaExtraUrbano(Integer idTariffaExtra, int km, float prezzo)
    {
        this.idTariffaExtra = idTariffaExtra;
        this.km = km;
        this.prezzo = prezzo;
    }

    public Integer getIdTariffaExtra()
    {
        return idTariffaExtra;
    }

    public void setIdTariffaExtra(Integer idTariffaExtra)
    {
        this.idTariffaExtra = idTariffaExtra;
    }

    public int getKm()
    {
        return km;
    }

    public void setKm(int km)
    {
        this.km = km;
    }

    public float getPrezzo()
    {
        return prezzo;
    }

    public void setPrezzo(float prezzo)
    {
        this.prezzo = prezzo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idTariffaExtra != null ? idTariffaExtra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoTariffaExtraUrbano))
        {
            return false;
        }
        CostoTariffaExtraUrbano other = (CostoTariffaExtraUrbano) object;
        if ((this.idTariffaExtra == null && other.idTariffaExtra != null) || (this.idTariffaExtra != null && !this.idTariffaExtra.equals(other.idTariffaExtra)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "database.entity.CostoTariffaExtraUrbano[ idTariffaExtra=" + idTariffaExtra + " ]";
    }
    
}
