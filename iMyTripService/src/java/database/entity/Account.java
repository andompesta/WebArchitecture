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
import javax.persistence.Id;
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
@Table(name = "Account")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByUserName", query = "SELECT a FROM Account a WHERE a.userName = :userName"),
    @NamedQuery(name = "Account.findByUserNameAndPass", query = "SELECT a FROM Account a WHERE a.userName = :userName and a.pass = :pass"),
    @NamedQuery(name = "Account.findByPass", query = "SELECT a FROM Account a WHERE a.pass = :pass"),
    @NamedQuery(name = "Account.findByMail", query = "SELECT a FROM Account a WHERE a.mail = :mail"),
    @NamedQuery(name = "Account.findByAbilitato", query = "SELECT a FROM Account a WHERE a.abilitato = :abilitato"),
    @NamedQuery(name = "Account.findByNome", query = "SELECT a FROM Account a WHERE a.nome = :nome"),
    @NamedQuery(name = "Account.findByCognome", query = "SELECT a FROM Account a WHERE a.cognome = :cognome"),
    @NamedQuery(name = "Account.findByDataNascita", query = "SELECT a FROM Account a WHERE a.dataNascita = :dataNascita"),
    @NamedQuery(name = "Account.deleteConto", query = "DELETE FROM Conto as c WHERE c.userName = :userName"),
    @NamedQuery(name = "Account.deleteLocalita", query = "DELETE FROM LocalitaPrivata as l WHERE l.userName = :userName")
})
public class Account extends Rc implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UserName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Pass")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Abilitato")
    private boolean abilitato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Cognome")
    private String cognome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DataNascita")
    private String dataNascita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName", fetch = FetchType.EAGER)
    private List<LocalitaPrivata> localitaPrivataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName", fetch = FetchType.EAGER)
    private List<Conto> contoList;

    public Account()
    {
    }

    public Account(String userName)
    {
        this.userName = userName;
    }

    public Account(String userName, String pass, String mail, boolean abilitato, String nome, String cognome, String dataNascita)
    {
        this.userName = userName;
        this.pass = pass;
        this.mail = mail;
        this.abilitato = abilitato;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public boolean getAbilitato()
    {
        return abilitato;
    }

    public void setAbilitato(boolean abilitato)
    {
        this.abilitato = abilitato;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }

    public String getDataNascita()
    {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita)
    {
        this.dataNascita = dataNascita;
    }

    @XmlTransient
    public List<LocalitaPrivata> getLocalitaPrivataList()
    {
        return localitaPrivataList;
    }

    public void setLocalitaPrivataList(List<LocalitaPrivata> localitaPrivataList)
    {
        this.localitaPrivataList = localitaPrivataList;
    }

    @XmlTransient
    public List<Conto> getContoList()
    {
        return contoList;
    }

    public void setContoList(List<Conto> contoList)
    {
        this.contoList = contoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account))
        {
            return false;
        }
        Account other = (Account) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "database.entity.Account[ userName=" + userName + " ]";
    }
    
}
