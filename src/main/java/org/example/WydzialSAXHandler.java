package org.example;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WydzialSAXHandler extends DefaultHandler {
    private String nazwa;
    private StringBuilder adres = new StringBuilder();
    private int liczbaPokoi;
    private String imie;
    private String nazwisko;
    private int wiek;
    private boolean czyBudynek;
    private boolean czyPracownik;
    private boolean czyStudent;

    private boolean czyAdres;
    private boolean czyLiczbaPokoi;
    private boolean czyWiek;

    Wydzial wydzial = new Wydzial();

    public WydzialSAXHandler() {
        czyBudynek = false;
        czyPracownik = false;
        czyStudent = false;

        czyAdres = false;
        czyLiczbaPokoi = false;
        czyWiek = false;
    }

    @Override
    public void startDocument() throws SAXException {
        wydzial = new Wydzial();
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
        if (qName.equals("Wydzial")) {
            wydzial.ustawNazwe(attributes.getValue("nazwa"));
        } else if (qName.equals("Budynek")) {
            czyBudynek = true;
            nazwa = attributes.getValue("nazwa");
        } else if (qName.equals("Pracownik")) {
            czyPracownik = true;
            imie = attributes.getValue("imie");
            nazwisko = attributes.getValue("nazwisko");
        } else if (qName.equals("Student")) {
            czyStudent = true;
            imie = attributes.getValue("imie");
            nazwisko = attributes.getValue("nazwisko");
        } else if (qName.equals("Adres")) {
            czyAdres = true;
        } else if (qName.equals("LiczbaPokoi")) {
            czyLiczbaPokoi = true;
        } else if (qName.equals("Wiek")) {
            czyWiek = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws org.xml.sax.SAXException {
        if (qName.equals("Budynek")) {
            czyBudynek = false;
            wydzial.dodajBudynek(new Budynek(nazwa, adres.toString(), liczbaPokoi));
            adres = new StringBuilder();
        } else if (qName.equals("Pracownik")) {
            czyPracownik = false;
            wydzial.dodajPracownika(new Osoba(imie, nazwisko, wiek));
        } else if (qName.equals("Student")) {
            czyStudent = false;
            wydzial.dodajStudenta(new Osoba(imie, nazwisko, wiek));
        } else if (qName.equals("Adres")) {
            czyAdres = false;
        } else if (qName.equals("LiczbaPokoi")) {
            czyLiczbaPokoi = false;
        } else if (qName.equals("Wiek")) {
            czyWiek = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException {
        String text = new String(ch, start, length);

        if (czyBudynek) {
            if (czyAdres) {
                adres.append(text);
            } else if (czyLiczbaPokoi) {
                liczbaPokoi = Integer.parseInt(text);
            }
        } else if (czyPracownik) {
            if (czyWiek) {
                wiek = Integer.parseInt(text);
            }
        } else if (czyStudent) {
            if (czyWiek) {
                wiek = Integer.parseInt(text);
            }
        }
    }

    public Wydzial getWydzial() {
        return wydzial;
    }
}
