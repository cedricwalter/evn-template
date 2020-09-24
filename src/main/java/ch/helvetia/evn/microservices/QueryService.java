package ch.helvetia.evn.microservices;

import ch.helvetia.evn.pojo.Nachweis;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class QueryService {

    public Map<Object, Object> searchWithPaging(String stammnummer, int page) {

        // see https://quarkus.io/guides/hibernate-orm-panache
        PanacheQuery<Nachweis> foundNachweis = Nachweis.find("stammnummer", stammnummer);

        foundNachweis.page(Page.ofSize(10));
        List<Nachweis> found = foundNachweis.page(Page.of(page, 10)).list();

        Map<Object, Object> result = new HashMap();
        result.put("numberOfPages", foundNachweis.pageCount());
        result.put("count", foundNachweis.count());
        result.put("found", found);

        return result;
    }
}
