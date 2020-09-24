package ch.helvetia.evn.kafka;

import ch.helvetia.evn.kafka.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CLSAdapter {

    private static final Logger LOG = Logger.getLogger(CLSAdapter.class);

    @Incoming("nachweis")  // source
    @Outgoing("nachweis") // sink
    @Broadcast  // Indicates that the item are dispatched to all subscribers
    public Nachweis process(Nachweis nachweis) {
        if ("submit-cls".equals(nachweis.getType())) {
            LOG.info(String.format("submit ", nachweis.stammNummer ));

            // validate

            // do a soap call in here

            // set new type
           nachweis.type = "cls-submitted";
        }
        return nachweis;
    }

}
