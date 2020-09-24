package ch.helvetia.evn.controller;

import ch.helvetia.evn.microservices.DataService;
import ch.helvetia.evn.microservices.QueryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/ui")
public class UIController {

    @Inject
    QueryService queryService;

    @Inject
    DataService dataService;

    @POST
    public void createNachweis(String stammNummer, String brand, String marke) {
        dataService.createNachweis(stammNummer, brand, marke);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> searchWithPaging(String stammNummer, int page) {
        return queryService.searchWithPaging(stammNummer, page);
    }
}
