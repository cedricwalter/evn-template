package ch.helvetia.evn.kafka;

import ch.helvetia.evn.kafka.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "prices" Kafka topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class CLSSoap {

    @Incoming("nachweis")  // source
    @Outgoing("nachweis") // sink
    @Broadcast  // Indicates that the item are dispatched to all subscribers
    public Nachweis process(Nachweis nachweis) {
        if ("submit-cls".equals(nachweis.getType())) {

            // validate

            // do a soap call in here

            // set new type
           nachweis.type = "cls-submitted";
        }
        return nachweis;
    }

}
