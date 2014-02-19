/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iMyTripJsonInterface;

import java.util.UUID;
import servicesoap.accountservice.Conto;

/**
 *
 * @author tlmarco
 */
public class contoParam
{
    private Conto conto;
    private UUID uuid;

    public contoParam(Conto conto, UUID uuid)
    {
        this.conto = conto;
        this.uuid = uuid;
    }

    public Conto getConto()
    {
        return conto;
    }

    public void setConto(Conto conto)
    {
        this.conto = conto;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }
    
}
