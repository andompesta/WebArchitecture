/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceSoap.accountService;

import database.entity.Account;
import database.entity.Conto;
import database.entity.LocalitaPrivata;
import database.entity.LocalitaPubblica;
import database.entity.Movimento;
import database.entity.Rc;
import database.session.AccountFacade;
import database.session.ContoFacade;
import database.session.LocalitaPrivataFacade;
import database.session.LocalitaPubblicaFacade;
import database.session.MovimentoFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import utils.MailService1;

/**
 *
 * @author tlmarco
 */
@WebService(serviceName = "accountSOAP")
public class accountSOAP
{
    
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private ContoFacade contoFacade;
    @EJB
    private LocalitaPrivataFacade locPrvF;
    @EJB
    private LocalitaPubblicaFacade locPubF;
    @EJB
    private MovimentoFacade movF;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getAccount")
    public List<Account> getAccount()
    {
        List<Account> accs = new ArrayList<Account>();
        accs = (List<Account>) accountFacade.findAll();
        if(accs.isEmpty())
        {
            //da modificare creando la classe rics e estendendo rc anche per lei
            Account acc = new Account();
            acc.setRc(-1);
            acc.setDescription("Nessun account trovato");
            accs.add(acc);
            return accs;
        }
        else
        {
            for(Account a : accs)
            {
                Iterator cIt = a.getContoList().iterator();
                while(cIt.hasNext())
                {
                    Conto c = (Conto) cIt.next();
                    c.setUserName(null);
                    for(Movimento mov : c.getMovimentoList())
                    {
                        mov.setIdConto(null);
                    }
                }
                Iterator locIt = a.getLocalitaPrivataList().iterator();
                while(locIt.hasNext())
                {
                    LocalitaPrivata locPr = (LocalitaPrivata) locIt.next();
                    locPr.setUserName(null);
                }
            }
            return accs;
        }
    }
    
    @WebMethod(operationName = "getAccountByuNameAndPass")
    public Account getAccountByuNameAndPass(@WebParam(name = "userName")String uName,
                                            @WebParam(name = "password")String pass)
    {   
        Account acc = accountFacade.getAccountByNameandPass(uName,pass);
        if(acc.getRc()  == -1)
        {
            //da modificare creando la classe rics e estendendo rc anche per lei
            return acc;
        }
        else{
            Iterator cIt = acc.getContoList().iterator();
            while(cIt.hasNext())
            {
                Conto c = (Conto) cIt.next();
                c.setUserName(null);
                for(Movimento mov : c.getMovimentoList())
                {
                    mov.setIdConto(null);
                }
            }
            Iterator locIt = acc.getLocalitaPrivataList().iterator();
            while(locIt.hasNext())
            {
                LocalitaPrivata locPr = (LocalitaPrivata) locIt.next();
                locPr.setUserName(null);
            }
            return acc;
        }
    }
    
    
    @WebMethod(operationName = "getAccountByName")
    public Account getAccountByuName(@WebParam(name = "Nome")String name)
    {
        Account acc = accountFacade.find(name);
        if(acc  == null)
        {
            acc = new Account();
            acc.setRc(-1);
            acc.setDescription("Nessun account trovto.");
            return acc;
        }
        else{
            Iterator cIt = acc.getContoList().iterator();
            while(cIt.hasNext())
            {
                Conto c = (Conto) cIt.next();
                c.setUserName(null);
                for(Movimento mov : c.getMovimentoList())
                {
                    mov.setIdConto(null);
                }
            }
            Iterator locIt = acc.getLocalitaPrivataList().iterator();
            while(locIt.hasNext())
            {
                LocalitaPrivata locPr = (LocalitaPrivata) locIt.next();
                locPr.setUserName(null);
            }
            return acc;
        }
    }
    
    @WebMethod(operationName = "createAccount")
    public Rc createAccount(@WebParam(name = "Account") Account acc)
    { 
        try{
            acc.setAbilitato(false);
            acc.setLocalitaPrivataList(null);
            acc.setContoList(null);
            Rc ret = accountFacade.creaAccount(acc);
            return ret;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("Errore nella creazione dell'account");
            return ret;
        }
    }
    
    @WebMethod(operationName = "updateAccount")
    public Account updateAccount(@WebParam(name = "Account") Account acc)
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
            acc = accountFacade.updateLocalità(acc);
            if(!(acc.getLocalitaPrivataList().isEmpty()))
            {
                Iterator locIt = acc.getLocalitaPrivataList().iterator();
                while(locIt.hasNext())
                {
                    LocalitaPrivata locPr = (LocalitaPrivata) locIt.next();
                    locPr.setUserName(null);
                }
            }
            if(!(acc.getContoList().isEmpty()))
            {
                Iterator cIt = acc.getContoList().iterator();
                while(cIt.hasNext())
                {
                    Conto c = (Conto) cIt.next();
                    c.setUserName(null);
                }
            }
            
            return acc;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            UUID old = acc.getUuid();
            String uName = acc.getUserName();
            acc = getAccountByuName(uName);
            acc.setRc(-1);
            acc.setDescription("Errore nell'update dell'account");
            acc.setUuid(old);
            return acc;
        }
    }
    
    @WebMethod(operationName = "enableAccount")
    public Rc enableAccount(@WebParam(name = "Account") Account acc)
    {
        try{
            acc.setAbilitato(true);
            accountFacade.edit(acc);
            Rc ret = new Rc();
            ret.setDescription("Abilitazione avvenuta con successo");
            return ret;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("Errore nell'abilitazione dell'account");
            return ret;
        }
    }
    
    
    @WebMethod(operationName = "restPass")
    public Rc restPass(@WebParam(name = "emailUtente") String mail)
    {
        try{
            Account acc = accountFacade.findByMail(mail);
            if(acc.getRc()==-1)
            {
                Rc ret = new Rc();
                ret.setRc(-1);
                ret.setDescription(acc.getDescription());
                return ret;
            }
            //generate new pass
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String newPass = sb.toString();
            acc.setPass(newPass);
            
            accountFacade.resetPassword(acc,newPass);
            Rc ret = new Rc();
            ret.setRc(0);
            ret.setDescription(acc.getPass());
            return ret;
        }
        catch(Exception e)
        {
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("Errore nel resettare la password---> "+e.getMessage());
            return ret;
        }
    }
    
    @WebMethod(operationName = "getLocaitaPubbliche")
    public List<LocalitaPubblica> getLocaitaPubbliche()
    {
        
        List<LocalitaPubblica> locPub = new ArrayList<LocalitaPubblica>();
        try
        {
            locPub = (List<LocalitaPubblica>) locPubF.findAll();
            if(locPub.isEmpty())
            {
                LocalitaPubblica loc = new LocalitaPubblica();
                loc.setRc(-1);
                loc.setDescription("Nessuna località trovata");
                locPub.add(loc);
            }
            return locPub;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            LocalitaPubblica loc = new LocalitaPubblica();
            loc.setRc(-1);
            loc.setDescription("Errore nel server");
            locPub.add(loc);
            return locPub;
        }
    }
    
    
    @WebMethod(operationName = "eseguiBonifico")
    public Account eseguiBonifico(@WebParam(name = "userName") String userName,@WebParam(name = "idConto") int idConto,
                             @WebParam(name = "prezzo") float prezzo,@WebParam(name = "descrizione") String descr)
    {
        Account acc = new Account();
        try{
            acc = accountFacade.find(userName);
            contoFacade.eseguiPagamento(acc, prezzo, idConto,descr);
            Iterator newcIt = acc.getContoList().iterator();                   
            while(newcIt.hasNext())
            {
                Conto c = (Conto) newcIt.next();
                c.setUserName(null);
            }
            for(LocalitaPrivata loc : acc.getLocalitaPrivataList())
            {
                loc.setUserName(null);
            }
            return acc;    
        }
        catch(Exception e)
        {
            acc = getAccountByuName(userName);
            acc.setRc(-1);
            acc.setDescription("Errore nell'update dell'account");
            e.printStackTrace();
            return acc;
        }
    }
    
    @WebMethod(operationName = "deleteConto")
    public boolean deleteConto(@WebParam(name = "contoToDelete") int idConto)
    {
        try{
            Conto c = contoFacade.find(idConto);
            contoFacade.remove(c);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod(operationName = "deleteLocalitaPrivata")
    public boolean deleteLocalitaPrivata(@WebParam(name = "localitaToDelete") int idLocalita)
    {
        try{
            LocalitaPrivata loc = locPrvF.find(idLocalita);
            locPrvF.remove(loc);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod(operationName = "getMovimenti")
    public List<Movimento> getMovimenti(@WebParam(name = "id") int idConto)
    {
        try{
            Conto c = new Conto();
            c.setIdConto(idConto);
            List<Movimento> movs = (List<Movimento>) movF.getMovimentiByidConto(c);
            for(Movimento mov : movs){
                mov.setIdConto(null);
            }
            return movs;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
