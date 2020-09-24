package ch.helvetia.evn.microservices.adapter;

import ch.helvetia.evn.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CLSAdapterService {

    private static final Logger LOG = Logger.getLogger(CLSAdapterService.class);

    @Incoming("nachweis")  // source
    @Outgoing("nachweis") // sink
    @Broadcast  // Indicates that the item are dispatched to all subscribers
    public Nachweis process(Nachweis nachweis) {
        if ("submit-cls".equals(nachweis.type)) {
            LOG.info(String.format("submit ", nachweis.stammNummer ));

            // validate

            // do a soap call in here

            // set new type
           nachweis.type = "cls-submitted";
        }
        return nachweis;
    }

}
