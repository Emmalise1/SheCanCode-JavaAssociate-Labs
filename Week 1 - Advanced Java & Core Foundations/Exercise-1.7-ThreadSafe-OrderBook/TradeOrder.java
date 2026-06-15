public class TradeOrder {
    private String id;
<<<<<<< HEAD
    private String type;  // "BUY" or "SELL"
=======
    private String type;  
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
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
