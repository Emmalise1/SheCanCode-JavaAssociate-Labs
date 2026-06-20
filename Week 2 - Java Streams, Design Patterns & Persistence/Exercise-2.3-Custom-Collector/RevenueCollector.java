import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class RevenueCollector implements Collector<ParallelLineItem, RevenueReport, RevenueReport> {

    @Override
    public Supplier<RevenueReport> supplier() {
        return () -> new RevenueReport(0.0, 0, 0.0);
    }

    @Override
    public BiConsumer<RevenueReport, ParallelLineItem> accumulator() {
        return (report, item) -> {
            double revenue = item.getRevenue();
            report.totalRevenue += revenue;
            report.itemCount++;
            report.maxRevenue = Math.max(report.maxRevenue, revenue);
        };
    }

    @Override
    public BinaryOperator<RevenueReport> combiner() {
        return (r1, r2) -> new RevenueReport(
                r1.totalRevenue + r2.totalRevenue,
                r1.itemCount + r2.itemCount,
                Math.max(r1.maxRevenue, r2.maxRevenue)
        );
    }

    @Override
    public Function<RevenueReport, RevenueReport> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
