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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tlmarco
 */
@Entity
@Table(name = "LocalitaPubblica")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "LocalitaPubblica.findAll", query = "SELECT l FROM LocalitaPubblica l"),
    @NamedQuery(name = "LocalitaPubblica.findByIdLocalita", query = "SELECT l FROM LocalitaPubblica l WHERE l.idLocalita = :idLocalita"),
    @NamedQuery(name = "LocalitaPubblica.findByDescrizione", query = "SELECT l FROM LocalitaPubblica l WHERE l.descrizione = :descrizione"),
    @NamedQuery(name = "LocalitaPubblica.findByLatitudine", query = "SELECT l FROM LocalitaPubblica l WHERE l.latitudine = :latitudine"),
    @NamedQuery(name = "LocalitaPubblica.findByLongitudine", query = "SELECT l FROM LocalitaPubblica l WHERE l.longitudine = :longitudine")
})
public class LocalitaPubblica extends Rc implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdLocalita")
    private Integer idLocalita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Descrizione")
    private String descrizione;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Latitudine")
    private double latitudine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Longitudine")
    private double longitudine;

    public LocalitaPubblica()
    {
    }

    public LocalitaPubblica(Integer idLocalita)
    {
        this.idLocalita = idLocalita;
    }

    public LocalitaPubblica(Integer idLocalita, String descrizione, double latitudine, double longitudine)
    {
        this.idLocalita = idLocalita;
        this.descrizione = descrizione;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Integer getIdLocalita()
    {
        return idLocalita;
    }

    public void setIdLocalita(Integer idLocalita)
    {
        this.idLocalita = idLocalita;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public double getLatitudine()
    {
        return latitudine;
    }

    public void setLatitudine(double latitudine)
    {
        this.latitudine = latitudine;
    }

    public double getLongitudine()
    {
        return longitudine;
    }

    public void setLongitudine(double longitudine)
    {
        this.longitudine = longitudine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idLocalita != null ? idLocalita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalitaPubblica))
        {
            return false;
        }
        LocalitaPubblica other = (LocalitaPubblica) object;
        if ((this.idLocalita == null && other.idLocalita != null) || (this.idLocalita != null && !this.idLocalita.equals(other.idLocalita)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "database.entity.LocalitaPubblica[ idLocalita=" + idLocalita + " ]";
    }
    
}
