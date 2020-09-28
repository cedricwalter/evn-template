package ch.helvetia.evn.microservices;

import ch.helvetia.evn.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class DataService {

    @Incoming("nachweis")
    @Outgoing("nachweis")
    public Nachweis createNachweis(String stammNummer, String brand, String marke) {
        Nachweis nachweis = new Nachweis(stammNummer, brand, marke);
        nachweis.type = "new";
        return nachweis;
    }

    @Incoming("nachweis-speichern")
    @Blocking // mandatory when using DB
    @Transactional // mandatory when using DB
    public void store(Nachweis nachweis) {
        // if !nachweis.transient for example
        nachweis.persist();
    }

}
