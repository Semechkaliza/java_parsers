package dao;

import entity.Card;
import entity.CardEnum;
import service.AbstractOldCardsBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OldCardsStAXBuilder extends AbstractOldCardsBuilder {
    private HashSet<Card> oldCards = new HashSet<>();
    private XMLInputFactory inputFactory;

    public OldCardsStAXBuilder() {
        System.out.println("stax");
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Card> getOldCards() {
        return oldCards;
    }

    public void buildSetOldCards(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (CardEnum.valueOf(name.toUpperCase()) == CardEnum.CARD) {
                        Card st = buildCard(reader);
                        oldCards.add(st);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Card buildCard(XMLStreamReader reader) throws XMLStreamException {
        Card st = new Card();
        st.setStatus(reader.getAttributeValue(null, CardEnum.STATUS.getValue()));
        st.setType(reader.getAttributeValue(null, CardEnum.TYPE.getValue())); // проверить на null
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CardEnum.valueOf(name.toUpperCase())) {
                        case THEMA:
                            st.setThema(getXMLText(reader));
                            break;
                        case YEAR:
                            name = getXMLText(reader);
                            st.setYear(Integer.parseInt(name));
                            break;
                        case COUNTRY:
                            st.setCountry(getXMLText(reader));
                            break;
                        case AUTHOR:
                            st.setAuthor(getXMLText(reader));
                            break;
                        case VALUABLE:
                            st.setValuable(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CardEnum.valueOf(name.toUpperCase()) == CardEnum.CARD) {
                        return st;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Card");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}