package ch.helvetia.evn.microservices.adapter;

import ch.helvetia.evn.pojo.EVN;
import ch.helvetia.evn.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class CLSAdapterService {

    private static final Logger LOG = Logger.getLogger(CLSAdapterService.class);

    @Inject
    @Channel("nachweis-pdf")
    Emitter<Nachweis> pdfGeneratorService;

    @Incoming("nachweis-validiert")  // source
    @Outgoing("nachweis-uebermittelt") // sink
    @Broadcast  // Indicates that the item are dispatched to all subscribers
    public EVN process(Nachweis nachweis) {
        LOG.info(String.format("submit ", nachweis.stammNummer));

        // validate

        // do a soap call in here

        // service can also send message to another topic
        pdfGeneratorService.send(nachweis);

        // goes in Outgoing topic
        EVN evn = new EVN();
        evn.evnId = UUID.randomUUID().toString();

        return evn;
    }

}
