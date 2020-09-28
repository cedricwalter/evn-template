package ch.helvetia.evn.pojo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

// PanacheEntity provide default Id and getter/setter
// this POJO can be persisted
@Entity
public class EVN extends PanacheEntity {

    public String nachweisId;  // TODO how to do proper references
    public String evnId;

    @Transient
    public String notPersistedExample;

    public EVN() {
    }

    public static EVN findByName(String evnId){
        return find("evnId", evnId).firstResult();
    }

}
