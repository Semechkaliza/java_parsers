package entity;

public enum CardEnum {
    OLDCARDS("oldCards"),
    STATUS("status"),
    TYPE("type"),
    CARD("card"),
    THEMA("thema"),
    YEAR("year"),
    COUNTRY("country"),
    AUTHOR("author"),
    VALUABLE("valuable");
    private String value;
    private CardEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}