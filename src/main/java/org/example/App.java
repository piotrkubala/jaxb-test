package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
        try {
            marshal();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void marshal() throws JAXBException, IOException {
        Wydzial wydzial = new Wydzial();
        wydzial.ustawNazwe("WEAIiIB");
        wydzial.dodajBudynek(new Budynek("C200", "ul. Polna 1", 10));
        wydzial.dodajBudynek(new Budynek("C201", "ul. Polna 3", 15));
        wydzial.dodajPracownika(new Osoba("Jan", "Kowalski", 30));
        wydzial.dodajPracownika(new Osoba("Arystoteles", "Stagiryta", 50));
        wydzial.dodajStudenta(new Osoba("Patryk", "Knapek", 20));
        wydzial.dodajStudenta(new Osoba("Jan", "Nowak", 20));

        JAXBContext context = JAXBContext.newInstance(Wydzial.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(wydzial, new File("./wydzial.xml"));
    }
}
