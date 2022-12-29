package org.example;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Budynek")
@XmlType(propOrder = { "nazwa", "liczbaPokoi", "adres" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Budynek {
    @XmlAttribute(name = "nazwa")
    String nazwa;
    @XmlElement(name = "Adres")
    String adres;
    @XmlElement(name = "LiczbaPokoi")
    int liczbaPokoi;

    public Budynek() {
    }

    public Budynek(String nazwa, String adres, int liczbaPokoi) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.liczbaPokoi = liczbaPokoi;
    }

    public void ustawNazwe(String nazwa) {
        this.nazwa = nazwa;
    }

    public void ustawAdres(String adres) {
        this.adres = adres;
    }

    public void ustawLiczbePokoi(int liczbaPokoi) {
        this.liczbaPokoi = liczbaPokoi;
    }

    public String pobierzNazwe() {
        return nazwa;
    }

    public String pobierzAdres() {
        return adres;
    }

    public int pobierzLiczbePokoi() {
        return liczbaPokoi;
    }

    @Override
    public String toString() {
        return String.format("Budynek: %s, adres: %s, liczba pokoi: %d", nazwa, adres, liczbaPokoi);
    }
}
