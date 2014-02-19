/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iMyTripJsonInterface;

import accountManager.AccountManagerLogic;
import com.google.gson.Gson;
import googleMapLogic.GoogleMapLogic;
import googleMapLogic.entity.Step;
import googleMapLogic.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import servicesoap.accountservice.Account;
import servicesoap.accountservice.AccountSOAP_Service;
import servicesoap.accountservice.Conto;
import servicesoap.accountservice.LocalitaPubblica;
import servicesoap.accountservice.Movimento;
import servicesoap.accountservice.Rc;
import servicesoap.googlemapservice.TripRequest;

/**
 *
 * @author tlmarco
 */
@Path("/interface")
public class Interface
{
    @Context
    private ServletContext context;
        
    @EJB
    private GoogleMapLogic gm;
    
    /**
     * Creates a new instance of RouteInterface
     */
    public Interface() {
        gm = new GoogleMapLogic();
    }

    @OPTIONS
    @Path("/logIn")
    public Response logIn(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/resetPass")
    public Response resetPass(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/generalPosition")
    public Response getLuoghiGenerali(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/logOut")
    public Response logOut(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/register")
    public Response register(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/updateUser")
    public Response updateUser(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/bonifico")
    public Response bonifico(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/addCard")
    public Response corsAddCard(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/removeCard")
    public Response corsRemoveCard(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/removeLocation")
    public Response corsRemoveLocation(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/getMovimenti")
    public Response corsGetMovimenti(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        Response.ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @POST
    @Path("/logIn")
    @Consumes("application/json")
    @Produces("application/json")
    public Response logIn(loginParam info,@Context HttpServletResponse serverResponse)
    {
        
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        
        AccountManagerLogic aLogic = new AccountManagerLogic();
        //(AccountManagerLogic) context.getAttribute("AccountManagerLogic"+);
        //    context.setAttribute("AccountManagerLogic", aLogic);
        boolean isCorrect = aLogic.chekAccountForLogin(info.getUserName(), info.getPassword());
        if(isCorrect)
        {
            UUID uuid = UUID.randomUUID();
            aLogic.getAccount().setUuid(uuid.toString());
            context.setAttribute("AccountManagerLogic_"+uuid.toString(), aLogic);
        }
        Gson gson = new Gson();
        String accJson = gson.toJson(aLogic.getAccount());
        return Response.ok(accJson).build();
    }
    
    @POST
    @Path("/logOut")
    @Consumes("application/json")
    @Produces("application/json")
    public Response logOut(String uuidJson,@Context HttpServletResponse serverResponse)
    {
        
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson gson = new Gson();
        String uuid = gson.fromJson(uuidJson,String.class);
        
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+uuid);
        context.removeAttribute("AccountManagerLogic_"+uuid);
        
        Rc ret = new Rc();
        ret.setRc(0);
        ret.setDescription("Logout eseguito");
        
        if(aLogic != null)
        {
            aLogic.preDestroy();
            aLogic.remove();
        }
        else
        {
            ret.setRc(-1);
            ret.setDescription("Sessione non trovata per il logOut");
        }
        String retJson = gson.toJson(ret);
        return Response.ok(retJson).build();
    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response register(Account acc,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        
        

        AccountSOAP_Service service = new servicesoap.accountservice.AccountSOAP_Service();
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        try{
            Rc result = port.createAccount(acc);
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            Gson gson = new Gson();
            String retJson = gson.toJson(result);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(retJson).build();
        }
        catch(Exception ex)
        {
            Rc retError = new Rc();
            retError.setRc(-1);
            retError.setDescription("errore nella creazione dell'account");
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(retError).build();
        }
        
    }
    
    @POST
    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response updateUser(Account acc,@Context HttpServletResponse serverResponse)
    {
        try
        {
            serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
            serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
            serverResponse.addHeader("Access-Control-Allow-Origin", "*");
            serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
            serverResponse.addHeader("Access-Control-Max-Age", "60");

            Gson gson = new Gson();
            String uuid = gson.fromJson(acc.getUuid(),String.class);
            //fare controllo sempre se è loggato
            AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+uuid);
            if(aLogic != null) {
                Account result = (Account) aLogic.updateAccaount(acc);
                String retJson = gson.toJson(result);
                return Response.ok(retJson).build();
            }
            else{
                acc.setRc(-1);
                acc.setUuid(null);
                acc.setDescription("Prego eseguire il login");
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(gson.toJson(acc)).build();
            }
        }
        catch(Exception e)
        {
            Gson gson = new Gson();
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("errore in updateUser : "+e.getMessage());
            String retJson = gson.toJson(ret);
            return Response.ok(retJson).build();
            
        }
    }
    
    @GET
    @Path("/enable/{uName}")
    @Produces(MediaType.TEXT_HTML)
    public Response enableAccount(@PathParam("uName") String uName, @Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        
        AccountSOAP_Service service = new servicesoap.accountservice.AccountSOAP_Service();
        servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
        
        Account acc = port.getAccountByName(uName);
        if(!acc.isAbilitato()){
            Rc success = port.enableAccount(acc);
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.TEXT_HTML).entity(success.getDescription()).build();
        }
        else{
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.TEXT_HTML).entity("Utente gia abilitato").build(); 
        }
            
    }
    
    @GET
    @Path("/generalPosition")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLuoghiGenerali(@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        try
        {   
            AccountSOAP_Service service = new servicesoap.accountservice.AccountSOAP_Service();
            servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();   
            List<LocalitaPubblica> locPub = port.getLocaitaPubbliche();
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            String retJson = jsonCreator.toJson(locPub);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(retJson).build();
        }
        catch(Exception ex)
        {
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("Errore nella getLuoghiGenerali: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            String retJson = jsonCreator.toJson(ret);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(retJson).build();
        }
    }
    
    @POST
    @Path("/resetPass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(String emailJson,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        String email = jsonCreator.fromJson(emailJson,String.class);
        try
        {
            AccountSOAP_Service service = new servicesoap.accountservice.AccountSOAP_Service();
            servicesoap.accountservice.AccountSOAP port = service.getAccountSOAPPort();
            Rc ret = port.restPass(email);
            ret.setDescription("La password e' stata cambiata con successo");
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(ret)).build();
        }
        catch(Exception ex)
        {
            Rc ret = new Rc();
            ret.setRc(-1);
            ret.setDescription("Errore nella resetPassword: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(ret)).build();
        }
    }
    
    @POST
    @Path("/bonifico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response bonifico(String jsonBonifico,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        bonificoParam param = jsonCreator.fromJson(jsonBonifico, bonificoParam.class);
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+param.getUuid());
        Account ret = new Account();
        try
        {
            if(aLogic != null){
                ret = aLogic.eseguiBonifico(param.getIdContoCorrente(), param.getPrezzo(),param.getDescrizione());
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(ret)).build();
            }
            else{
                
                ret.setRc(-1);
                ret.setUuid(null);
                ret.setDescription("Prego eseguire il login");
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(ret)).build();
            }
        }
        catch(Exception ex)
        {
            ret = aLogic.getAccount();
            ret.setRc(-1);
            ret.setDescription("Errore nell'esecuzione del pagamento: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(ret)).build();
        }
    }
    
    @POST
    @Path("/addCard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuovoConto(String jsonConto,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        contoParam param = jsonCreator.fromJson(jsonConto, contoParam.class);
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+param.getUuid());
        Account acc = new Account();
        try
        {
            if(aLogic != null){
                acc = aLogic.getAccount();
                Conto c = param.getConto();
                c.setIdConto(null);
                acc.getContoList().add(c);
                acc = aLogic.updateAccaount(acc);
                
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
            else{
                acc.setRc(-1);
                acc.setUuid(null);
                acc.setDescription("Prego eseguire il login");
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
        }
        catch(Exception ex)
        {
            acc.setRc(-1);
            acc.setDescription("Errore nell'esecuzione del pagamento: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
        }
    }
    
    @POST
    @Path("/removeCard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response rimuoviConto(String jsonConto,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        contoParam param = jsonCreator.fromJson(jsonConto, contoParam.class);
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+param.getUuid());
        Account acc = new Account();
        try
        {
            if(aLogic != null){
                acc = aLogic.getAccount();
                Conto c = param.getConto();
                acc = aLogic.deleteConto(acc, c.getIdConto());
                
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
            else{
                acc.setRc(-1);
                acc.setUuid(null);
                acc.setDescription("Prego eseguire il login");
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
        }
        catch(Exception ex)
        {
            acc.setRc(-1);
            acc.setDescription("Errore nell'esecuzione del pagamento: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
        }
    }
    
    @POST
    @Path("/removeLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeLocation(String jsonLocation,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        localitaParam param = jsonCreator.fromJson(jsonLocation, localitaParam.class);
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+param.getUuid());
        Account acc = new Account();
        try
        {
            if(aLogic != null){
                acc = aLogic.getAccount();
                acc = aLogic.deleteLocalitaPrv(acc, param.getIdLocalita());
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
            else{
                acc.setRc(-1);
                acc.setUuid(null);
                acc.setDescription("Prego eseguire il login");
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
            }
        }
        catch(Exception ex)
        {
            acc.setRc(-1);
            acc.setDescription("Errore nell'esecuzione del pagamento: ---->"+ex.getMessage());
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(acc)).build();
        }
    }
    
    @POST
    @Path("/getMovimenti")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovimentiForConto(String jsonConto,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        contoParam param = jsonCreator.fromJson(jsonConto, contoParam.class);
        AccountManagerLogic aLogic = (AccountManagerLogic) context.getAttribute("AccountManagerLogic_"+param.getUuid());
        ArrayList<Movimento> movs = new ArrayList<Movimento>();
        try
        {
            if(aLogic != null){
                movs = aLogic.getMovimenti(param.getConto().getIdConto());
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(movs)).build();
            }
            else{
                Movimento mov = new Movimento();
                mov.setRc(-1);
                mov.setDescription("Prego eseguire il login");
                movs.add(mov);
                Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
                return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(movs)).build();
            }
        }
        catch(Exception ex)
        {
            Movimento mov = new Movimento();
            mov.setRc(-1);
            mov.setDescription("Errore nell'esecuzione del pagamento: ---->"+ex.getMessage());
            movs.add(mov);
            Response.ResponseBuilder rBuild = Response.status(Response.Status.OK);
            return rBuild.type(MediaType.APPLICATION_JSON).entity(jsonCreator.toJson(movs)).build();
        }
    }
    
    /**
     * Sets th Allow-Origin to * so as to permit CORS calls
     * @param requestH
     * @return 
     */
    @OPTIONS
    @Path("/computeRoute")
    public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }
    
    @OPTIONS
    @Path("/computeTicket")
    public Response crosTicket(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        ResponseBuilder rb = Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Max-Age", "60");
        if (!"".equals(requestH)) {
            rb.header("Access-Control-Allow-Headers", requestH);
        }
        return rb.build();
    }

    /**
     * POST method for updating or creating an instance of RouteInterface
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("/computeRoute")
    @Consumes("application/json")
    @Produces("application/json")
    public Response compteRoute(TripRequest tRequest,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        servicesoap.googlemapservice.Rc ret = new servicesoap.googlemapservice.Rc();
        try
        {
            //GoogleMapLogic gm = new GoogleMapLogic();
            ret = gm.getRoot(tRequest);
            ResponseBuilder response = Response.ok(jsonCreator.toJson(ret),MediaType.APPLICATION_JSON);
            return response.build();
        }
        catch(Exception e)
        {
            ret.setRc(-1);
            ret.setDescription("Errore in computeRoute:"+ e.getMessage());
            ResponseBuilder response = Response.ok(jsonCreator.toJson(ret),MediaType.APPLICATION_JSON);
            return response.build();
        }
    }
    
    @POST
    @Path("/computeTicket")
    @Consumes("application/json")
    @Produces("application/json")
    public Response computeTicket(List<Step> steps,@Context HttpServletResponse serverResponse)
    {
        serverResponse.addHeader("Allow-Control-Allow-Methods", "POST,GET,OPTIONS");
        serverResponse.addHeader("Access-Control-Allow-Credentials", "true");
        serverResponse.addHeader("Access-Control-Allow-Origin", "*");
        serverResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        serverResponse.addHeader("Access-Control-Max-Age", "60");
        Gson jsonCreator = new Gson();
        Ticket tks = new Ticket();
        try
        {
            //GoogleMapLogic gm = new GoogleMapLogic();
            tks = gm.computePriceTicket(steps);
            ResponseBuilder response = Response.ok(jsonCreator.toJson(tks),MediaType.APPLICATION_JSON);
            return response.build();
        }
        catch(Exception e)
        {
            Ticket tk = new Ticket();
            tk.setRc(-1);
            tk.setDescription("Errore nel server-->"+e.getMessage());
            ResponseBuilder response = Response.ok(jsonCreator.toJson(tks),MediaType.APPLICATION_JSON);
            return response.build();
        }
    }
    
    
}
