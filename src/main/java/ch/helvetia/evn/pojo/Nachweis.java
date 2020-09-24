package ch.helvetia.evn.pojo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

// PanacheEntity provide default Id and getter/setter
// this POJO can be persisted
@Entity
public class Nachweis extends PanacheEntity {

    // columns are all public fields
    public String stammNummer;
    public String brand;
    public String marke;
    public String type; // TODO enum

    @Transient
    public String notPersistedExample;

    public Nachweis() {
    }

    public Nachweis(String stammNummer, String brand, String marke) {
        this.stammNummer = stammNummer;
        this.brand = brand;
        this.marke = marke;
    }

    public String getType() {
        return type;
    }

    public static Nachweis findByName(String stammNummer){
        return find("stammNummer", stammNummer).firstResult();
    }

    public static List<Nachweis> findByType(String type){
        return list("type", type);
    }

}
