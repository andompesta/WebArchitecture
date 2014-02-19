/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.session;

import database.entity.Account;
import database.entity.Conto;
import database.entity.Movimento;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tlmarco
 */
@Stateless
public class MovimentoFacade extends AbstractFacade<Movimento>
{
    @PersistenceContext(unitName = "iMyTripServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public MovimentoFacade()
    {
        super(Movimento.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Movimento> getMovimentiByidConto(Conto c) throws Exception
    {
        try{
            Query q = em.createNamedQuery("Movimento.findByIdConto");
            q.setParameter("idConto", c);
            List<Movimento> movs = (List<Movimento>) q.getResultList();
            //em.flush();
            return movs;
        }
        catch(Exception ex)
        {
            throw ex;
        }
        
    }
    
}
