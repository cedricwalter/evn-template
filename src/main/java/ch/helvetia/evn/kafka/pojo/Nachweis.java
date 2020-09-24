package ch.helvetia.evn.kafka.pojo;

public class Nachweis {

    public String stammNummer;
    public String brand;
    public String marke;
    public String type; // TODO enum

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
}
