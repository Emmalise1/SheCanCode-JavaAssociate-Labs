public class RevenueReport {
    public double totalRevenue;
    public long itemCount;
    public double maxRevenue;

    public RevenueReport(double totalRevenue, long itemCount, double maxRevenue) {
        this.totalRevenue = totalRevenue;
        this.itemCount = itemCount;
        this.maxRevenue = maxRevenue;
    }

    @Override
    public String toString() {
        return String.format("RevenueReport{total=%.2f, items=%d, max=%.2f}",
                totalRevenue, itemCount, maxRevenue);
    }
}
