public class Currency {

    String id;
    String description;
    String symbol;
    int decimal_places;

    public Currency(String id, String description, String symbol, int decimal_places) {
        this.id = id;
        this.description = description;
        this.symbol = symbol;
        this.decimal_places = decimal_places;
    }
}
