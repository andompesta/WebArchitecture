/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.session;

import database.entity.Account;
import database.entity.Conto;
import database.entity.LocalitaPrivata;
import database.entity.Rc;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.MailService1;

/**
 *
 * @author tlmarco
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account>
{
    @PersistenceContext(unitName = "iMyTripServicePU")
    private EntityManager em;
    @Resource
    private SessionContext context;


    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public AccountFacade()
    {
        super(Account.class);
    }
    
    public void flush()
    {
        em.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Account getAccountByNameandPass(String uName, String pass)
    {
        Query q = em.createNamedQuery("Account.findByUserNameAndPass");
        q.setParameter("userName", uName);
        q.setParameter("pass", pass);
        Account a = (Account) q.getSingleResult();
        return a;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account updateLocalità(Account acc)
    {
        try{
            
            em.flush();
            Account ret = new Account();
            ret = em.merge(acc);
            //utx.commit();
            ret.setRc(0);
            ret.setDescription("Update avvenuto con successo");
            ret.setUuid(acc.getUuid());
            em.flush();
            return ret;
        }
        catch(Exception e)
        {
            context.setRollbackOnly();
            acc.setRc(-1);
            acc.setDescription("Update non avvenuto");
            e.printStackTrace();
            return acc;
        }
        
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Account findByMail(String mail)
    {
        Account acc = new Account();
        try{
            Query q = em.createNamedQuery("Account.findByMail");
            q.setParameter("mail", mail);
            acc = (Account) q.getSingleResult();
            acc.setRc(0);
            acc.setDescription("account trovato con successo");
            return acc;
        }
        catch(Exception e)
        {
            acc.setRc(-1);
            acc.setDescription("Errore nel trovare l'account per la mail specificata");
            e.printStackTrace();
            return acc;
        }
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Rc creaAccount(Account acc) throws Exception
    {
        try{
            em.persist(acc);
            
            MailService1 ms = new MailService1();
            String msg ="<HTML>" +"Gentile utente "+acc.getNome()+", "+acc.getNome()+", per attitare il suo accaunt deve andare al link: "+
                        "<a href='http://maca-mac-pro.local:8080/iMyTripLogic/resources/account/enable/"+acc.getUserName()+"'>Abilita</a>"+"</HTML>";
            ms.SendMessage(acc.getMail(), "Abilita account", msg);
            
            Rc ret = new Rc();
            ret.setRc(0);
            ret.setDescription("Creazione avvenuta con successo");
            em.flush();
            
            return ret;
        }
        catch(Exception e)
        {
            context.setRollbackOnly();
            throw e;
        }
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void resetPassword(Account acc,String newPass) throws Exception
    {
        try{
            if(acc.getLocalitaPrivataList()!=null)
            {
                Iterator locIt = acc.getLocalitaPrivataList().iterator();
                while(locIt.hasNext())
                {
                    LocalitaPrivata locPr = (LocalitaPrivata) locIt.next();
                    locPr.setUserName(acc);
                }
            }
            if(acc.getContoList()!=null)
            {
                Iterator cIt = acc.getContoList().iterator();
                while(cIt.hasNext())
                {
                    Conto c = (Conto) cIt.next();
                    c.setUserName(acc);
                }
            }
            
            em.merge(acc);            
            MailService1 ms = new MailService1();
            String msg = "La nuova password e' stata resettata col valore: "+newPass;
            msg += ". Una volta entrare prego aggioranre l'account";
            ms.SendMessage(acc.getMail().trim(), "Reset Pass", msg);
            em.flush();
        }
        catch(Exception e)
        {
            context.setRollbackOnly();
            throw e;
        }
        
    }
    
}
