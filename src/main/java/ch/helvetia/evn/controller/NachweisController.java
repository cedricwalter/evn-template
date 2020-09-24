package ch.helvetia.evn.controller;

import ch.helvetia.evn.microservices.DataService;
import ch.helvetia.evn.microservices.QueryService;
import ch.helvetia.evn.pojo.Nachweis;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/nachweis")
public class NachweisController {

    @Inject
    QueryService queryService;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> searchWithPaging(String stammnummer, int page) {
        return queryService.searchWithPaging(stammnummer, page);
    }

}
