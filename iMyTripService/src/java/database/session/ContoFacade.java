/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.session;

import database.entity.Account;
import database.entity.Conto;
import database.entity.Movimento;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.MailService1;
import utils.PrintPDF1;


/**
 *
 * @author tlmarco
 */
@Stateless
public class ContoFacade extends AbstractFacade<Conto>
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

    public ContoFacade()
    {
        super(Conto.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void eseguiPagamento(Account acc,Float prezzo,int idConto,String descr) throws Exception 
    {
        try {
            if(acc.getContoList() != null){
                Iterator cIt = acc.getContoList().iterator();
                while(cIt.hasNext())
                {
                    Conto c = (Conto) cIt.next();
                    c.setUserName(acc);
                    if(c.getIdConto() == idConto)
                    {
                        Float newSaldo = c.getSaldo() + prezzo;
                        
                        DecimalFormat df = new DecimalFormat("###.##");

                        newSaldo = Float.parseFloat(df.format(newSaldo));
                        c.setSaldo(newSaldo);
                        Date adesso = new Date();
                        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Movimento mov = new Movimento();
                        mov.setData(sf.format(adesso));
                        mov.setImporto(prezzo);
                        mov.setDescrizione(descr);
                        mov.setIdConto(c);
                        c.getMovimentoList().add(mov);
                        em.merge(c);
                        em.flush();
                        
                        if(sendMailPDF(acc,prezzo))
                            break;
                    }
                }
            }
            em.flush();
        } catch (Exception e) {
            context.setRollbackOnly();
            throw e;
        }
    }
    
    private boolean sendMailPDF(Account acc, Float prezzo) throws FileNotFoundException, Exception
    {
        boolean send = false;
        try{
            PrintPDF1 printer = new PrintPDF1();
            String nameFile = printer.printdocumentoPdf(acc.getUserName(), prezzo.toString(), acc.getPass());
            if(nameFile != null && !(nameFile.isEmpty()))
            {
                MailService1 ms = new MailService1();
                ms.SendMessagePDF(acc.getMail(), "Biglietto", nameFile);
                send = true;
            }
            return send;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
