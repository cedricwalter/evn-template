package ch.helvetia.evn.controller;

import ch.helvetia.evn.pojo.Nachweis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nachweis")
public class NachweisController {

    // TODO just to demonstrate testing
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nachweis> getAllNachweis() {
        return Nachweis.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nachweis> getAllNachweisOfType(String type) {
        return Nachweis.list("type", type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAllNachweisOfType(String type) {
        Nachweis.delete("type", type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteNachweisById(String id) {
        Nachweis.deleteById(id);
    }



}
