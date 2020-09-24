package ch.helvetia.evn.microservices;

import ch.helvetia.evn.pojo.Nachweis;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class DataService {

    @Incoming("nachweis")
    @Blocking
    @Transactional
    public void store(Nachweis nachweis) {
        // if !nachweis.transient for example
        nachweis.persist();
    }

}
