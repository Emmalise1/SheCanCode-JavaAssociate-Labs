public class OrderBookTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Exercise 1.7: Thread-Safe Order Book ===\n");

        OrderBookEngine orderBook = new OrderBookEngine();

        Thread[] buyers = new Thread[5];
        Thread[] sellers = new Thread[5];


        for (int i = 0; i < 5; i++) {
            final int id = i;
            buyers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    TradeOrder order = new TradeOrder("B" + id + "-" + j, "BUY", "AAPL", 100, 150.0);
                    orderBook.addOrder(order);
                    orderBook.matchOrders();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }, "Buyer-" + i);
        }

        for (int i = 0; i < 5; i++) {
            final int id = i;
            sellers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    TradeOrder order = new TradeOrder("S" + id + "-" + j, "SELL", "AAPL", 100, 149.0);
                    orderBook.addOrder(order);
                    orderBook.matchOrders();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }, "Seller-" + i);
        }

        System.out.println("Starting 5 buyers and 5 sellers...\n");
        for (Thread t : buyers) t.start();
        for (Thread t : sellers) t.start();

        for (Thread t : buyers) t.join();
        for (Thread t : sellers) t.join();

        System.out.println("\n=== Results ===");
        System.out.println("Total matches: " + orderBook.getMatchCount());
        System.out.println("Remaining buy orders: " + orderBook.getBuyCount());
        System.out.println("Remaining sell orders: " + orderBook.getSellCount());

    }
}
