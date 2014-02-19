/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iMyTripJsonInterface;

import java.util.UUID;
import servicesoap.accountservice.LocalitaPrivata;

/**
 *
 * @author tlmarco
 */
public class localitaParam
{
    private int idLocalita;
    private UUID uuid;

    public localitaParam(int idLocalita, UUID uuid)
    {
        this.idLocalita = idLocalita;
        this.uuid = uuid;
    }

    public localitaParam()
    {
    }

    public int getIdLocalita()
    {
        return idLocalita;
    }

    public void setIdLocalita(int idLocalita)
    {
        this.idLocalita = idLocalita;
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
