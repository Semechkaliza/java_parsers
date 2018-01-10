package service;

import entity.Card;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractOldCardsBuilder {
    protected Set<Card> oldCards;

    public AbstractOldCardsBuilder() {
        oldCards = new HashSet<Card>();
    }

    public Set<Card> getOldCards() {
        return oldCards;
    }

    abstract public void buildSetOldCards(String fileName);
}