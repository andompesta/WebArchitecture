/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceSoap.googleMapService;

import database.entity.Rc;
import database.session.CostoTariffaExtraUrbanoFacade;
import googleMaps.adapter.GoogleMapAdapter;
import googleMaps.entity.TripRequest;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tlmarco
 */
@WebService(serviceName = "NewWebService")
public class SOAPGoogle
{
    @EJB
    private CostoTariffaExtraUrbanoFacade ceF;

    @WebMethod(operationName = "iMyTripJson")
    public Rc iMyTripJson(@WebParam(name = "GoogleRequest") TripRequest trReq)
    {
        try
        {
            GoogleMapAdapter gmBean = new GoogleMapAdapter();
            String url = gmBean.GetGoogleMapsRequestUrl(trReq);
            String ret = gmBean.computeRouteGoogle(url);
            Rc retCode = new Rc();
            retCode.setRc(0);
            retCode.setDescription(ret);
            return retCode;
        }
        catch(Exception ex)
        {
            Rc retCode = new Rc();
            retCode.setRc(-1);
            retCode.setDescription("Errore nel calcolo della rotta-->"+ex.getMessage());
            return retCode;
        }
        
    }
    
//    @WebMethod(operationName = "computeTicket")
//    public ArrayList<Ticket> computeTicket(@WebParam(name = "steps") List<Step> steps)
//    {
//        ArrayList<Ticket> tks = new ArrayList<Ticket>();
//        try
//        {
//            GoogleMapAdapter gmBean = new GoogleMapAdapter();
//            tks = gmBean.computeTicket(steps);
//            return tks;
//        }
//        catch(Exception ex)
//        {
//            Ticket tk = new Ticket();
//            tk.setRc(-1);
//            tk.setDescription("Errore nel server-->"+ex.getMessage());
//            tks.add(tk);
//            return tks;
//        }
//        
//    }

    @WebMethod(operationName = "getJsonSOAP")
    public TripRequest getJson() {
        long zero = 0;
        TripRequest tr = new TripRequest(false,new String() ,new String(),"WALKING",zero,zero);
        return tr;
    }
    
    @WebMethod(operationName = "getPrezzoTariffeExtraUrbane")
    public float getPrezzoTariffeExtraUrbane(int km)
    {
        float prezzo = 0;
        try
        {
            prezzo = ceF.getPrezzoForKm(km);
            return prezzo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
}
