public class Electronics extends WarehouseItem {
    private String brand;
    private int warrantyMonths;

    public Electronics(String id, String name, String category, double price,
                       String brand, int warrantyMonths) {
        super(id, name, category, price);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() { return brand; }
    public int getWarrantyMonths() { return warrantyMonths; }
}
