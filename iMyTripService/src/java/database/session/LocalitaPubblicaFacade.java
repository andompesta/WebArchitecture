/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database.session;

import database.entity.LocalitaPubblica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tlmarco
 */
@Stateless
public class LocalitaPubblicaFacade extends AbstractFacade<LocalitaPubblica>
{
    @PersistenceContext(unitName = "iMyTripServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public LocalitaPubblicaFacade()
    {
        super(LocalitaPubblica.class);
    }
    
}
