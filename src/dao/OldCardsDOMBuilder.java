package dao;

import entity.Card;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.AbstractOldCardsBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OldCardsDOMBuilder extends AbstractOldCardsBuilder {
    private Set<Card> oldCards;
    private DocumentBuilder docBuilder;
    public OldCardsDOMBuilder() {
        System.out.println("dom");
        this.oldCards = new HashSet<Card>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try { docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }

    }

    public Set<Card> getOldCards() {
        return oldCards;
    }

    public void buildSetOldCards(String fileName) {
        Document doc = null;
        try{
        doc = docBuilder.parse(fileName);
        Element root = doc.getDocumentElement();
        NodeList oldCardsList = root.getElementsByTagName("card");
        for (int i = 0; i < oldCardsList.getLength(); i++) {
            Element cardElement = (Element) oldCardsList.item(i);
            Card card = buildCard(cardElement);
            oldCards.add(card);
        }
    }
        catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
          }
    }
    private Card buildCard(Element cardElement) {
        Card card = new Card();
        card.setType(cardElement.getAttribute("type"));
        card.setThema(getElementTextContent(cardElement, "thema"));
        card.setCountry(getElementTextContent(cardElement, "country"));
        card.setAuthor(getElementTextContent(cardElement, "author"));
        card.setValuable(getElementTextContent(cardElement, "valuable"));
        Integer data = Integer.parseInt(getElementTextContent( cardElement,"year"));
        card.setYear(data);
        card.setStatus(cardElement.getAttribute("status"));
        return card;
    }
     private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}