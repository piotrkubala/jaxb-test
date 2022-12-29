package org.example;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Wydzial")
@XmlType(propOrder = { "nazwa", "budynki", "pracownicy", "studenci" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Wydzial {
    @XmlRootElement(name = "Budynki")
    @XmlType(propOrder = { "budynki" })
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Budynki {
        @XmlElement(name = "Budynek")
        List<Budynek> budynki = new ArrayList<>();

        public Budynki() {
        }

        public void dodajBudynek(Budynek budynek) {
            budynki.add(budynek);
        }

        public List<Budynek> pobierzBudynki() {
            return budynki;
        }
    }

    @XmlRootElement(name = "Pracownicy")
    @XmlType(propOrder = { "pracownicy" })
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Pracownicy {
        @XmlElement(name = "Pracownik")
        List<Osoba> pracownicy = new ArrayList<Osoba>();

        public Pracownicy() {
        }

        public void dodajPracownika(Osoba pracownik) {
            pracownicy.add(pracownik);
        }

        public List<Osoba> pobierzPracownikow() {
            return pracownicy;
        }
    }

    @XmlRootElement(name = "Studenci")
    @XmlType(propOrder = { "studenci" })
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Studenci {
        @XmlElement(name = "Student")
        List<Osoba> studenci = new ArrayList<Osoba>();

        public Studenci() {
        }

        public void dodajStudenta(Osoba student) {
            studenci.add(student);
        }

        public List<Osoba> pobierzStudentow() {
            return studenci;
        }
    }

    @XmlTransient
    String tegoNieChce = "tegoNieChce";

    @XmlAttribute(name = "nazwa")
    String nazwa;

    @XmlElement(name = "Budynki")
    Budynki budynki = new Budynki();

    @XmlElement(name = "Pracownicy")
    Pracownicy pracownicy = new Pracownicy();

    @XmlElement(name = "Studenci")
    Studenci studenci = new Studenci();

    public Wydzial() {
    }

    public Wydzial(String nazwa) {
        this.nazwa = nazwa;
    }

    public void ustawNazwe(String nazwa) {
        this.nazwa = nazwa;
    }

    public String pobierzNazwe() {
        return nazwa;
    }

    public void dodajBudynek(Budynek budynek) {
        budynki.dodajBudynek(budynek);
    }

    public void dodajPracownika(Osoba pracownik) {
        pracownicy.dodajPracownika(pracownik);
    }

    public void dodajStudenta(Osoba student) {
        studenci.dodajStudenta(student);
    }

    public List<Budynek> pobierzBudynki() {
        return budynki.pobierzBudynki();
    }

    public List<Osoba> pobierzPracownikow() {
        return pracownicy.pobierzPracownikow();
    }

    public List<Osoba> pobierzStudentow() {
        return studenci.pobierzStudentow();
    }
}
