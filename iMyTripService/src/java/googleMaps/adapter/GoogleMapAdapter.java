/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMaps.adapter;

import googleMaps.entity.TripRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author tlmarco
 */
public class GoogleMapAdapter
{
    public String computeRouteGoogle(String uri) throws Exception
    {
        try
        {
            URL googleUrl = new URL(uri);
            URLConnection googleMaps = googleUrl.openConnection();
            
            String totalJson = new String();
            BufferedReader in = new BufferedReader(new InputStreamReader(googleMaps.getInputStream()));
            String inputLine = new String();
            
            while ((inputLine = in.readLine()) != null) 
                totalJson += inputLine;
            
            in.close();
            return totalJson;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    
        /**
     * Gets the Uri Request to submit to google maps
     * @return The request URI for Google maps 
     */
    public String GetGoogleMapsRequestUrl(TripRequest trReq) {
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin="+trReq.getOrigin()+
                "&destination="+trReq.getDestination()+
                "&mode="+trReq.getMode()+
                "&sensor="+trReq.getSensor().toString()+
                "&departure_time="+trReq.getDepartureTimeMs().toString()+
                "&language=it";
        url = url.replaceAll(" ", "%20");
        System.out.println(url);
        return url;
    }
    
//    public ArrayList<Ticket> computeTicket(String json) throws Exception
//    {
//        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
//        try
//        {
//            int minutes = 0;
//            int km = 0;
//            ArrayList<String> urbanBus = new ArrayList<String>();
//            ArrayList<String> estraUrbanBus = new ArrayList<String>();
//            Gson jsonCreator = new Gson();
//
//            if(minutes != 0) {
//                Ticket urbanTicket = new Ticket();
//                urbanTicket.setNomeBus(urbanBus);
//                if(minutes <= 70) {
//                    urbanTicket.setPrezzo((float)0.9);
//                }
//                else{
//                    if( minutes <= 120 && minutes > 70) {
//                        urbanTicket.setPrezzo((float)1.2);
//                    }
//                    else{
//                        if( minutes >120) {
//                            urbanTicket.setPrezzo((float)2.3);
//                        }
//                    }
//                }
//                tickets.add(urbanTicket);
//            }
//            if(km != 0)
//            {
//                Ticket extraUrbanTicket = new Ticket();
//                extraUrbanTicket.setPrezzo((float)4);
//                extraUrbanTicket.setNomeBus(estraUrbanBus);
//                tickets.add(extraUrbanTicket);
//            }
//            return tickets;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            throw e;
//        }
//    }
    
//    private Step  xml_traverse_DOM(Node element,Step s)
//    {
//        if(element.getNodeName().equals("travel_mode")){
//            s.setTravelMode(element.getNodeValue());
//        }
//        if(element.getNodeName().equals("duration"))
//        {
//            googleMaps.generated.Step.Duration d = new googleMaps.generated.Step.Duration();
//            for (Node child = element.getFirstChild(); child != null;  child = child.getNextSibling())      
//            {
//                if(child.getNodeName().equals("text"))
//                {
//                    d.setText(child.getNodeValue());
//                    s.setDuration(d);
//                }
//            }
//        }
//        if(element.getNodeName().equals("distance"))
//        {
//            googleMaps.generated.Step.Distance d = new googleMaps.generated.Step.Distance();
//            for (Node child = element.getFirstChild(); child != null;  child = child.getNextSibling())      
//            {
//                if(child.getNodeName().equals("text"))
//                {
//                    d.setText(child.getNodeValue());
//                    s.setDistance(d);
//                }
//            }
//        }
//        for (Node child = element.getFirstChild(); child != null;  child = child.getNextSibling())      
//        {
//            xml_traverse_DOM(child,s);
//        }
//        return s;
//    }

}
