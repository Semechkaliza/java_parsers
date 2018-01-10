package service;

import dao.OldCardsDOMBuilder;
import dao.OldCardsSAXBuilder;
import dao.OldCardsStAXBuilder;

public class CardBuilderFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }
    public AbstractOldCardsBuilder createCardBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new OldCardsDOMBuilder();
            case STAX:
                return new OldCardsStAXBuilder();
            case SAX:
                return new OldCardsSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
