/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Movimento")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Movimento.findAll", query = "SELECT m FROM Movimento m"),
    @NamedQuery(name = "Movimento.findByIdMovimento", query = "SELECT m FROM Movimento m WHERE m.idMovimento = :idMovimento"),
    @NamedQuery(name = "Movimento.findByData", query = "SELECT m FROM Movimento m WHERE m.data = :data"),
    @NamedQuery(name = "Movimento.findByImporto", query = "SELECT m FROM Movimento m WHERE m.importo = :importo"),
    @NamedQuery(name = "Movimento.findByDescrizione", query = "SELECT m FROM Movimento m WHERE m.descrizione = :descrizione"),
    @NamedQuery(name = "Movimento.findByIdConto", query = "SELECT m FROM Movimento m WHERE m.idConto = :idConto")
})
public class Movimento implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdMovimento")
    private Integer idMovimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Data")
    private String data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Importo")
    private float importo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "Descrizione")
    private String descrizione;
    @JoinColumn(name = "IdConto", referencedColumnName = "IdConto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conto idConto;

    public Movimento()
    {
    }

    public Movimento(Integer idMovimento)
    {
        this.idMovimento = idMovimento;
    }

    public Movimento(Integer idMovimento, String data, float importo, String descrizione)
    {
        this.idMovimento = idMovimento;
        this.data = data;
        this.importo = importo;
        this.descrizione = descrizione;
    }

    public Integer getIdMovimento()
    {
        return idMovimento;
    }

    public void setIdMovimento(Integer idMovimento)
    {
        this.idMovimento = idMovimento;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public float getImporto()
    {
        return importo;
    }

    public void setImporto(float importo)
    {
        this.importo = importo;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public Conto getIdConto()
    {
        return idConto;
    }

    public void setIdConto(Conto idConto)
    {
        this.idConto = idConto;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMovimento != null ? idMovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimento))
        {
            return false;
        }
        Movimento other = (Movimento) object;
        if ((this.idMovimento == null && other.idMovimento != null) || (this.idMovimento != null && !this.idMovimento.equals(other.idMovimento)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "database.entity.Movimento[ idMovimento=" + idMovimento + " ]";
    }
    
}
