/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tlmarco
 */
@Entity
@Table(name = "Conto")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Conto.findAll", query = "SELECT c FROM Conto c"),
    @NamedQuery(name = "Conto.findByIdConto", query = "SELECT c FROM Conto c WHERE c.idConto = :idConto"),
    @NamedQuery(name = "Conto.findByTipo", query = "SELECT c FROM Conto c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Conto.findByCodiceCarta", query = "SELECT c FROM Conto c WHERE c.codiceCarta = :codiceCarta"),
    @NamedQuery(name = "Conto.findBySaldo", query = "SELECT c FROM Conto c WHERE c.saldo = :saldo")
})
public class Conto implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdConto")
    private Integer idConto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CodiceCarta")
    private String codiceCarta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Saldo")
    private float saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConto", fetch = FetchType.EAGER)
    private List<Movimento> movimentoList;
    @JoinColumn(name = "UserName", referencedColumnName = "UserName")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account userName;

    public Conto()
    {
    }

    public Conto(Integer idConto)
    {
        this.idConto = idConto;
    }

    public Conto(Integer idConto, String tipo, String codiceCarta, float saldo)
    {
        this.idConto = idConto;
        this.tipo = tipo;
        this.codiceCarta = codiceCarta;
        this.saldo = saldo;
    }

    public Integer getIdConto()
    {
        return idConto;
    }

    public void setIdConto(Integer idConto)
    {
        this.idConto = idConto;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getCodiceCarta()
    {
        return codiceCarta;
    }

    public void setCodiceCarta(String codiceCarta)
    {
        this.codiceCarta = codiceCarta;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(float saldo)
    {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Movimento> getMovimentoList()
    {
        return movimentoList;
    }

    public void setMovimentoList(List<Movimento> movimentoList)
    {
        this.movimentoList = movimentoList;
    }

    public Account getUserName()
    {
        return userName;
    }

    public void setUserName(Account userName)
    {
        this.userName = userName;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idConto != null ? idConto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conto))
        {
            return false;
        }
        Conto other = (Conto) object;
        if ((this.idConto == null && other.idConto != null) || (this.idConto != null && !this.idConto.equals(other.idConto)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "database.entity.Conto[ idConto=" + idConto + " ]";
    }
    
}
