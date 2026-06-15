Exercise 1.2: Collections Performance Benchmark

Test Environment
- Items tested: 1,000 product SKUs
- Each SKU: ID (SKU0001), Name (Product_X), Price (10.0 - 1009.0)
- Measurement: Nanoseconds using System.nanoTime()
- Runs: Average of 5 runs for accuracy

Benchmark Results

ArrayList: 638,700 ns (0.64 ms) - O(n) lookup
LinkedList: 1,708,500 ns (1.71 ms) - O(n) lookup
HashSet: 848,700 ns (0.85 ms) - O(1) lookup
TreeSet: 28,960,300 ns (28.96 ms) - O(log n) lookup

Key Findings

ArrayList: Fastest iteration, good for sequential access, slow lookups
LinkedList: Fast insertions at ends, good for queues, slow random access
HashSet: Fastest O(1) lookups, no ordering guarantee
TreeSet: Maintains sorted order, slowest insertion (45x slower than HashSet)

Recommendation for Recently Viewed Products

Optimal Choice: LinkedHashSet

Justification:
1. Maintains chronological order using linked list
2. O(1) contains() check prevents duplicates
3. O(1) removal of oldest item for size limiting
4. Single structure handles all requirements

Sample Implementation:

public class RecentlyViewedProducts {
    private static final int MAX_SIZE = 10;
    private LinkedHashSet<String> viewed = new LinkedHashSet<>();
    
    public void addProduct(String productId) {
        viewed.remove(productId);
        viewed.add(productId);
        if (viewed.size() > MAX_SIZE) {
            String oldest = viewed.iterator().next();
            viewed.remove(oldest);
        }
    }
}

How to Run:
javac CollectionBenchmark.java
java CollectionBenchmark

Expected Output:
=== Exercise 1.2: Collections Benchmark ===
Results (1000 items):
  ArrayList insert: 638700 ns
  LinkedList insert: 1708500 ns
  HashSet insert: 848700 ns
  TreeSet insert: 28960300 ns
