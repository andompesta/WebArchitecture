/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accountManager;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import servicesoap.accountservice.Account;
import servicesoap.accountservice.Conto;
import servicesoap.accountservice.LocalitaPubblica;
import servicesoap.accountservice.Movimento;
import servicesoap.accountservice.Rc;


/**
 *
 * @author tlmarco
 */
@Local
public interface AccountManagerLogicLocal
{
    public Account getAccount();
        
    boolean chekAccountForLogin(String userName, String pass);

    public Account updateAccaount(Account acc);

    public Account getAccountbyUserName(String uName);

    public List<LocalitaPubblica> getLocalitaGenerali();

    public Account eseguiBonifico(int idConto, float prezzo,String descrizione);
    
    public Account deleteConto(Account acc, int  idConto) throws Exception;
    
    public ArrayList<Movimento> getMovimenti(int idConto);
    
    public Account deleteLocalitaPrv(Account acc, int  idLocalita) throws Exception;
    
}
