public class TradeOrder {
    private String id;
    private String type;  // "BUY" or "SELL"
    private String symbol;
    private int quantity;
    private double price;

    public TradeOrder(String id, String type, String symbol, int quantity, double price) {
        this.id = id;
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s %s %d @ $%.2f", type, symbol, quantity, price);
    }
}
