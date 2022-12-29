package org.example;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Osoba")
@XmlType(propOrder = { "imie", "nazwisko", "wiek" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Osoba {
    @XmlAttribute(name = "imie")
    String imie;
    @XmlAttribute(name = "nazwisko")
    String nazwisko;
    @XmlElement(name = "Wiek")
    int wiek;

    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, int wiek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    public void ustawImie(String imie) {
        this.imie = imie;
    }

    public void ustawNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void ustawWiek(int wiek) {
        this.wiek = wiek;
    }

    public String pobierzImie() {
        return imie;
    }

    public String pobierzNazwisko() {
        return nazwisko;
    }

    public int pobierzWiek() {
        return wiek;
    }
}
