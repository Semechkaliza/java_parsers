package dao;

import entity.Card;
import org.junit.Assert;
import org.junit.Test;
import service.AbstractOldCardsBuilder;
import service.CardBuilderFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OldCardsSAXBuilderTest {
    @Test
    public void buildSetOldCards() throws Exception {
        Set<Card> expected = new HashSet<>();
        Card card= new Card("sent","Nature","congratulatory",2010,"Belarus","Repin","Historical");
        expected.add(card);
        Card card2= new Card("not sent","People","common",2012,"Russia","Perov","Collection");
        expected.add(card2);
        CardBuilderFactory sFactory = new CardBuilderFactory();
        AbstractOldCardsBuilder builder=sFactory.createCardBuilder("SAX");
        String path = "D:\\work\\java3.3\\web\\data\\";
        builder.buildSetOldCards(path+"test.xml");
        Set<Card> actual=builder.getOldCards();
        Assert.assertEquals(expected, actual);
    }

}