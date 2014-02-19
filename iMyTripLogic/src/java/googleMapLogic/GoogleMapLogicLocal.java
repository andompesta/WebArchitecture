/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleMapLogic;

import googleMapLogic.entity.Step;
import googleMapLogic.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import servicesoap.googlemapservice.Rc;

import servicesoap.googlemapservice.TripRequest;

/**
 *
 * @author tlmarco
 */
@Local
public interface GoogleMapLogicLocal
{

    Rc getRoot(TripRequest trReq);
    Ticket computePriceTicket(List<Step> steps) throws Exception;
    
}
