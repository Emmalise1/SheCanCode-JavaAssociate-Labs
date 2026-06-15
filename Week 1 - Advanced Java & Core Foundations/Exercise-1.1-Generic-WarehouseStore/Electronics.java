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
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
