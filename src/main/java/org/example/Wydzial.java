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

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Wydzial: %s%nPodaję listę budynków:%n", nazwa));

        if (budynki.pobierzBudynki().isEmpty()) {
            sb.append("NIC\nPozdrawiam\n");
        } else {
            for (Budynek budynek : budynki.pobierzBudynki()) {
                sb.append(String.format("%s%n", budynek.toString()));
            }
        }

        sb.append("\nPodaję listę pracowników:\n");
        if (pracownicy.pobierzPracownikow().isEmpty()) {
            sb.append("NIKT\nPozdrawiam\n");
        } else {
            for (Osoba pracownik : pracownicy.pobierzPracownikow()) {
                sb.append(String.format("%s%n", pracownik.toString()));
            }
        }

        sb.append("\nPodaję listę studentów:\n");
        if (studenci.pobierzStudentow().isEmpty()) {
            sb.append("NIKT\nPozdrawiam\n");
        } else {
            for (Osoba student : studenci.pobierzStudentow()) {
                sb.append(String.format("%s%n", student.toString()));
            }
        }

        return sb.toString();
    }
}
