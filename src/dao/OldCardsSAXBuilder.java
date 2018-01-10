package dao;

import entity.Card;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.AbstractOldCardsBuilder;
import service.CardHandler;

import java.io.IOException;
import java.util.Set;

public class OldCardsSAXBuilder extends AbstractOldCardsBuilder {
    private Set<Card> oldCards;
    private CardHandler sh;
    private XMLReader reader;
    public OldCardsSAXBuilder() {
        System.out.println("sax");
        sh = new CardHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }
    public Set<Card> getOldCards() {
        return oldCards;
    }
    public void buildSetOldCards(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        oldCards = sh.getOldCards();
    }
}