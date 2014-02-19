/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.session;

import database.entity.Account;
import database.entity.CostoTariffaExtraUrbano;
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
public class CostoTariffaExtraUrbanoFacade extends AbstractFacade<CostoTariffaExtraUrbano>
{
    @PersistenceContext(unitName = "iMyTripServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public CostoTariffaExtraUrbanoFacade()
    {
        super(CostoTariffaExtraUrbano.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public float getPrezzoForKm(int Km) throws Exception
    {
        try{
        Query q = em.createNamedQuery("CostoTariffaExtraUrbano.findPrezzoForKm");
        q.setParameter("km", Km);
        float prezzo = (Float) q.getSingleResult();
        return prezzo;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
