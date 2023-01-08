package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class App {
    public static void main(String[] args) {
        try {
            marshal();

            Wydzial odczytanyWydzial = unmarshal();

            System.out.println(odczytanyWydzial.toString());

            Wydzial odczytanyWydzialDOM = DOM_read();

            if (odczytanyWydzialDOM != null) {
                System.out.println(odczytanyWydzialDOM.toString());
            }

            Wydzial odczytanySAX = SAX_read();

            if (odczytanySAX != null) {
                System.out.println(odczytanySAX.toString());
            }
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
        wydzial.dodajStudenta(new Osoba("Razogarz", "Nowak", 20));

        JAXBContext context = JAXBContext.newInstance(Wydzial.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(wydzial, new File("./wydzial.xml"));
    }

    public static Wydzial unmarshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Wydzial.class);
        return (Wydzial) context.createUnmarshaller().unmarshal(new FileReader("./wydzial.xml"));
    }

    public static Wydzial DOM_read() {
        // stworzenie fabryki
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // stworzenie document buildera do parsowania pliku XML
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("./wydzial.xml"));

            doc.getDocumentElement().normalize();

            // odczytanie nazwy wydzialu
            Wydzial wydzial = new Wydzial(doc.getDocumentElement().getAttribute("nazwa"));

            NodeList list = doc.getElementsByTagName("Budynek");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);

                wydzial.dodajBudynek(new Budynek(
                        element.getAttribute("nazwa"),
                        element.getElementsByTagName("Adres").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("LiczbaPokoi").item(0).getTextContent())
                ));
            }

            NodeList list2 = doc.getElementsByTagName("Pracownik");

            for (int i = 0; i < list2.getLength(); i++) {
                Element element = (Element) list2.item(i);

                wydzial.dodajPracownika(new Osoba(
                        element.getAttribute("imie"),
                        element.getAttribute("nazwisko"),
                        Integer.parseInt(element.getElementsByTagName("Wiek").item(0).getTextContent())
                ));
            }

            NodeList list3 = doc.getElementsByTagName("Student");

            for (int i = 0; i < list3.getLength(); i++) {
                Element element = (Element) list3.item(i);

                wydzial.dodajStudenta(new Osoba(
                        element.getAttribute("imie"),
                        element.getAttribute("nazwisko"),
                        Integer.parseInt(element.getElementsByTagName("Wiek").item(0).getTextContent())
                ));
            }

            return wydzial;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Wydzial SAX_read() {
        try {
            // stworzenie fabryki i parsera
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            WydzialSAXHandler wydzialSAXHandler = new WydzialSAXHandler();

            // parsowanie
            saxParser.parse("./wydzial.xml", wydzialSAXHandler);

            Wydzial result = wydzialSAXHandler.getWydzial();

            return result;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
