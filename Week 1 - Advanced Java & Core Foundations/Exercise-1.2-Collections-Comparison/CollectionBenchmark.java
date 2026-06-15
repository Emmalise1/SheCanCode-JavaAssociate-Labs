import java.util.*;

public class CollectionBenchmark {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.2: Collections Benchmark ===\n");

        List<BenchmarkItem> items = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            items.add(new BenchmarkItem("SKU" + i, "Item_" + i, 10.0 + i));
        }

<<<<<<< HEAD
        // Testing ArrayList
=======
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
        long start = System.nanoTime();
        List<BenchmarkItem> arrayList = new ArrayList<>(items);
        long arrayListTime = System.nanoTime() - start;

<<<<<<< HEAD
        // Testing LinkedList
=======
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
        start = System.nanoTime();
        List<BenchmarkItem> linkedList = new LinkedList<>(items);
        long linkedListTime = System.nanoTime() - start;

<<<<<<< HEAD
        // Testing HashSet
=======
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
        start = System.nanoTime();
        Set<BenchmarkItem> hashSet = new HashSet<>(items);
        long hashSetTime = System.nanoTime() - start;

        System.out.println("Results (1000 items):");
        System.out.println("  ArrayList insert: " + arrayListTime + " ns");
        System.out.println("  LinkedList insert: " + linkedListTime + " ns");
        System.out.println("  HashSet insert: " + hashSetTime + " ns");

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
