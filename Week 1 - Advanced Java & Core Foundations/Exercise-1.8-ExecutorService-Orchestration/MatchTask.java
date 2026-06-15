public class MatchTask {
    private String buyOrderId;
    private String sellOrderId;
    private int quantity;
    private double price;

    public MatchTask(String buyOrderId, String sellOrderId, int quantity, double price) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Matched: %s <-> %s | %d shares @ $%.2f",
                buyOrderId, sellOrderId, quantity, price);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
