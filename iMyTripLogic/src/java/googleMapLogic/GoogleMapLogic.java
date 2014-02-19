/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMapLogic;

import googleMapLogic.entity.Step;
import googleMapLogic.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import servicesoap.googlemapservice.Rc;
import servicesoap.googlemapservice.TripRequest;

/**
 *
 * @author tlmarco
 */
@Stateless
public class GoogleMapLogic implements GoogleMapLogicLocal
{
    @Override
    public Rc getRoot(TripRequest trReq)
    {
        servicesoap.googlemapservice.NewWebService service = new servicesoap.googlemapservice.NewWebService();
        servicesoap.googlemapservice.SOAPGoogle port = service.getSOAPGooglePort();
        
        //SOAP Call
        Rc routeRet = port.iMyTripJson(trReq);
        return routeRet;
    }
    
    @Override
    public Ticket computePriceTicket(List<Step> steps) throws Exception
    {
        Ticket ticket = new Ticket();
        try
        {
            float minutes = 0;
            float km = 0;
            ArrayList<String> urbanBus = new ArrayList<String>();
            ArrayList<String> exstraUrbanBus = new ArrayList<String>();
            
            
            for(int i = 0; i < steps.size() ; i++){
                if(steps.get(i).getTravelMode().equals("TRANSIT")){
                    if(!(steps.get(i).getAgency().contains("Extraurbano"))){
                        minutes += Integer.parseInt(steps.get(i).getDuration().replace(" min", ""));
                        urbanBus.add(steps.get(i).getLine());
                    }
                    else{
                        String temp = steps.get(i).getDistance().replaceAll(",", ".");
                        km += Float.parseFloat(temp.replace(" km", ""));
                        exstraUrbanBus.add(steps.get(i).getLine());
                    }
                }
            }
            if(minutes != 0) {
                ticket.setNomeBus(urbanBus);
                if(minutes <= 70) {
                    float newPrezzo = ticket.getPrezzo()+(float)0.9;
                    ticket.setPrezzo(newPrezzo);
                }
                else{
                    if( minutes <= 120 && minutes > 70) {
                        float newPrezzo = ticket.getPrezzo()+(float)1.2;
                        ticket.setPrezzo(newPrezzo);
                    }
                    else{
                        if( minutes >120) {
                            float newPrezzo = ticket.getPrezzo()+(float)2.3;
                            ticket.setPrezzo(newPrezzo);
                        }
                    }
                }
            }
            if(km >= 4){
                float oldPrezzo = ticket.getPrezzo();
                for(int i = 0; i < exstraUrbanBus.size();i++)
                {
                    ticket.getNomeBus().add(exstraUrbanBus.get(i));
                }
                servicesoap.googlemapservice.NewWebService service = new servicesoap.googlemapservice.NewWebService();
                servicesoap.googlemapservice.SOAPGoogle port = service.getSOAPGooglePort();
                
                float newPrezzo = port.getPrezzoTariffeExtraUrbane((int)km);
                if( newPrezzo == 0){
                    ticket.setRc(-1);
                    ticket.setDescription("Errore nel calcolo del prezzo del biglietto");
                }
                ticket.setPrezzo(oldPrezzo+newPrezzo);
            }
            else{
                if(km < 4 && km != 0){
                    for(int i = 0; i < exstraUrbanBus.size();i++)
                    {
                        ticket.getNomeBus().add(exstraUrbanBus.get(i));
                    }
                }
            }
            return ticket;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

}
