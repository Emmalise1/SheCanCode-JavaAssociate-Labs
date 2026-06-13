import java.util.*;
import java.util.concurrent.*;

public class ExecutorOrchestrator {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Exercise 1.8: ExecutorService Orchestration ===\n");

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + processors);

        ExecutorService executor = Executors.newFixedThreadPool(processors);
        List<Future<MatchTask>> futures = new ArrayList<>();

        List<String> buyOrders = new ArrayList<>();
        List<String> sellOrders = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            buyOrders.add("BUY-" + i);
            sellOrders.add("SELL-" + i);
        }

        System.out.println("Created " + buyOrders.size() + " buy orders and " +
                sellOrders.size() + " sell orders\n");

        long startTime = System.nanoTime();

        for (int i = 0; i < Math.min(buyOrders.size(), sellOrders.size()); i++) {
            final int index = i;
            Future<MatchTask> future = executor.submit(() -> {
                // Simulate matching work
                Thread.sleep(1);
                return new MatchTask(buyOrders.get(index), sellOrders.get(index), 100, 150.0);
            });
            futures.add(future);
        }

        int matched = 0;
        for (Future<MatchTask> future : futures) {
            try {
                MatchTask result = future.get();
                matched++;
                if (matched <= 5) { 
                    System.out.println("  " + result);
                }
            } catch (ExecutionException e) {
                System.out.println("Matching failed: " + e.getCause());
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\n=== Results ===");
        System.out.println("Total matches: " + matched);
        System.out.println("Time taken: " + String.format("%.2f", duration) + " ms");
        System.out.println("Throughput: " + (int)(matched / (duration / 1000)) + " matches/sec");

    }
}
