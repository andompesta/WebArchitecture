/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountManager;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.xml.ws.WebServiceRef;
import servicesoap.accountservice.Account;
import servicesoap.accountservice.AccountSOAP_Service;
import servicesoap.accountservice.LocalitaPubblica;
import servicesoap.accountservice.Movimento;

/**
 *
 * @author tlmarco
 */
@Stateful
public class AccountManagerLogic implements AccountManagerLogicLocal
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/iMyTripService/accountSOAP.wsdl")
    private AccountSOAP_Service service = new servicesoap.accountservice.AccountSOAP_Service();
    private Account acc;
    
    @Override
    public boolean chekAccountForLogin(String userName, String pass)
    {
        //Call service
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        try{
            acc = port.getAccountByuNameAndPass(userName, pass);
            if(acc.getRc() == 0)
            {
                //L'account è stato trovato
                if(acc.isAbilitato())
                {
                    acc.setDescription("Account identificato e abilitato");
                    return true;
                }
                else
                {
                    //account non abilitato
                    acc.setRc(-1);
                    acc.setDescription("Account non autenticato, prego autenticare account tramite mail");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            acc = new Account();
            acc.setRc(-1);
            acc.setDescription("Errore nella login");
            return false;
        }
        return false;
    }
    
    public synchronized Account getAccount()
    {
        return acc;
    }
    

    
    @Override
    public Account updateAccaount(Account acc)
    {
        //Call service
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        this.acc = port.updateAccount(acc);
        return this.acc;
    }

    
//    @Override
//    public Rc registerAccount(Account acc)
//    {
//        //Call service
//        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
//        Rc res = port.createAccount(acc);
//        return res;
//    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println(acc.getUserName());
        System.out.println(acc.getPass());
        this.acc = null;
    }
    @Remove
    public void remove() {
        System.out.println("Bean rimosso");
    }

    @Override
    public Account getAccountbyUserName(String uName)
    {
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        this.acc = port.getAccountByName(uName);
        return acc;
    }

    @Override
    public List<LocalitaPubblica> getLocalitaGenerali()
    {
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        return port.getLocaitaPubbliche();
    }

    @Override
    public Account eseguiBonifico(int idConto, float prezzo,String descrizione)
    {
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        Account ret = port.eseguiBonifico(acc.getUserName(),idConto, prezzo,descrizione);
        ret.setUuid(this.acc.getUuid());
        this.acc = ret;
        return ret;
    }
    
    @Override
    public Account deleteConto(Account acc, int  idConto) throws Exception
    {
        try{
            servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
            boolean removed = false;
            removed = port.deleteConto(idConto);
            if(removed)
            {
                for(int i = 0; i < acc.getContoList().size();i++)
                {
                    if(acc.getContoList().get(i).getIdConto() == idConto){
                        acc.getContoList().remove(i);
                        break;
                    }
                }
                acc.setRc(0);
                this.acc = acc;
                return acc;
            }
            else{
                acc.setRc(-1);
                acc.setDescription("conto non rimosso");
                return acc;
            }
        }
        catch(Exception e){
            throw e;
        }
        
    }
    
    @Override
    public Account deleteLocalitaPrv(Account acc, int  idLocalita) throws Exception
    {
        try{
            servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
            boolean removed = false;
            removed = port.deleteLocalitaPrivata(idLocalita);
        
            if(removed){
                for(int i = 0; i < acc.getLocalitaPrivataList().size();i++)
                {
                    if(acc.getLocalitaPrivataList().get(i).getIdLocalita() == idLocalita){
                        acc.getLocalitaPrivataList().remove(i);
                        break;
                    }
                }
                acc.setRc(0);
                this.acc = acc;
                return acc;
            }
            else{
                acc.setRc(-1);
                acc.setDescription("Localita non rimossa");
                return acc;
            }
        }
        catch(Exception e){
            throw e;
        }    
    }
    @Override
    public ArrayList<Movimento> getMovimenti(int idConto)
    {
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        ArrayList<Movimento> movs = (ArrayList<Movimento>) port.getMovimenti(idConto);
        if(movs!=null)
        {
            return movs;
        }
        else{
            Movimento mov = new Movimento();
            mov.setRc(-1);
            movs.add(mov);
            return movs;
        }
    }

}
