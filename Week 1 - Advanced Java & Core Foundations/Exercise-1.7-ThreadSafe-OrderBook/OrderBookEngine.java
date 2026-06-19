import java.util.*;
import java.util.concurrent.locks.*;

public class OrderBookEngine {
    private final List<TradeOrder> buyOrders = new ArrayList<>();
    private final List<TradeOrder> sellOrders = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private int matchCount = 0;

    public void addOrder(TradeOrder order) {
        lock.lock();
        try {
            if (order.getType().equals("BUY")) {
                buyOrders.add(order);
                System.out.println(Thread.currentThread().getName() + " added BUY: " + order);
            } else {
                sellOrders.add(order);
                System.out.println(Thread.currentThread().getName() + " added SELL: " + order);
            }
        } finally {
            lock.unlock();
        }
    }

    public void matchOrders() {
        lock.lock();
        try {
            if (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
                TradeOrder buy = buyOrders.remove(0);
                TradeOrder sell = sellOrders.remove(0);
                matchCount++;
                System.out.println(Thread.currentThread().getName() + " MATCHED: " +
                        buy.getId() + " with " + sell.getId());
            }
        } finally {
            lock.unlock();
        }
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getBuyCount() {
        return buyOrders.size();
    }

    public int getSellCount() {
        return sellOrders.size();
    }
}

