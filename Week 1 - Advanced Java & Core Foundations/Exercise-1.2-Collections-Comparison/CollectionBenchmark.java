import java.util.*;

public class CollectionBenchmark {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.2: Collections Benchmark ===\n");

        // Create 1,000 test items
        List<BenchmarkItem> items = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            items.add(new BenchmarkItem("SKU" + i, "Item_" + i, 10.0 + i));
        }

        // Test ArrayList
        long start = System.nanoTime();
        List<BenchmarkItem> arrayList = new ArrayList<>(items);
        long arrayListInsertTime = System.nanoTime() - start;

        // Test ArrayList lookup
        start = System.nanoTime();
        boolean foundArrayList = arrayList.contains(items.get(500));
        long arrayListLookupTime = System.nanoTime() - start;

        // Test LinkedList
        start = System.nanoTime();
        List<BenchmarkItem> linkedList = new LinkedList<>(items);
        long linkedListInsertTime = System.nanoTime() - start;

        // Test LinkedList lookup
        start = System.nanoTime();
        boolean foundLinkedList = linkedList.contains(items.get(500));
        long linkedListLookupTime = System.nanoTime() - start;

        // Test HashSet
        start = System.nanoTime();
        Set<BenchmarkItem> hashSet = new HashSet<>(items);
        long hashSetInsertTime = System.nanoTime() - start;

        // Test HashSet lookup
        start = System.nanoTime();
        boolean foundHashSet = hashSet.contains(items.get(500));
        long hashSetLookupTime = System.nanoTime() - start;

        // Test TreeSet
        start = System.nanoTime();
        Set<BenchmarkItem> treeSet = new TreeSet<>(Comparator.comparing(BenchmarkItem::getSku));
        treeSet.addAll(items);
        long treeSetInsertTime = System.nanoTime() - start;

        // Test TreeSet lookup
        start = System.nanoTime();
        boolean foundTreeSet = treeSet.contains(items.get(500));
        long treeSetLookupTime = System.nanoTime() - start;

        // Print results
        System.out.println("Results for 1,000 items (nanoseconds):\n");
        System.out.println("Collection   | Insert Time | Lookup Time");
        System.out.println("-------------|-------------|------------");
        System.out.printf("ArrayList    | %11d | %11d%n", arrayListInsertTime, arrayListLookupTime);
        System.out.printf("LinkedList   | %11d | %11d%n", linkedListInsertTime, linkedListLookupTime);
        System.out.printf("HashSet      | %11d | %11d%n", hashSetInsertTime, hashSetLookupTime);
        System.out.printf("TreeSet      | %11d | %11d%n", treeSetInsertTime, treeSetLookupTime);

        // Print findings
        System.out.println("\n=== Key Findings ===");
        System.out.println("ArrayList: Fastest iteration, slow lookups O(n)");
        System.out.println("LinkedList: Good for ends, slow random access");
        System.out.println("HashSet: Fastest lookups O(1), no order");
        System.out.println("TreeSet: Maintains sorted order, slowest inserts");

        System.out.println("\n=== Recommendation for Recently Viewed Products ===");
        System.out.println("Best choice: LinkedHashSet");
        System.out.println("Reasons: Maintains order + O(1) lookups + no duplicates");
    }
}