package service;

import entity.Card;
import entity.CardEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CardHandler extends DefaultHandler {
    private Set<Card> oldCards;
    private Card current = null;
    private CardEnum currentEnum = null;
    private EnumSet<CardEnum> withText;
    public CardHandler() {
        oldCards = new HashSet<Card>();
        withText = EnumSet.range(CardEnum.THEMA, CardEnum.VALUABLE);
    }
    public Set<Card> getOldCards() {
        return oldCards;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("card".equals(localName)) {
            current = new Card();
            current.setStatus(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setType(attrs.getValue(1));
            }
        } else {
            CardEnum temp = CardEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
     public void endElement(String uri, String localName, String qName) {
        if ("card".equals(localName)) {
            oldCards.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
     if (currentEnum != null) {
         switch (currentEnum) {
             case THEMA:
                 current.setThema(s);
                 break;
             case YEAR:
                 current.setYear(Integer.parseInt(s));
                 break;
             case COUNTRY:
                 current.setCountry(s);
                 break;
             case AUTHOR:
                 current.setAuthor(s);
                 break;
             case VALUABLE:
                 current.setValuable(s);
                 break;
             default:
                 throw new EnumConstantNotPresentException(
                         currentEnum.getDeclaringClass(), currentEnum.name());
         }
     }
     currentEnum = null;
    }
}