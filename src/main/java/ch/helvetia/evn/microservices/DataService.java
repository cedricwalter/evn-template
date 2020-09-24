package ch.helvetia.evn.microservices;

import ch.helvetia.evn.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class DataService {

    @Outgoing("nachweis")
    public Nachweis createNachweis(String stammNummer, String brand, String marke) {
        Nachweis nachweis = new Nachweis(stammNummer, brand, marke);
        nachweis.type = "new";
        return nachweis;
    }

    @Incoming("nachweis")
    @Blocking
    @Transactional
    public void store(Nachweis nachweis) {
        // if !nachweis.transient for example
        nachweis.persist();
    }

}
