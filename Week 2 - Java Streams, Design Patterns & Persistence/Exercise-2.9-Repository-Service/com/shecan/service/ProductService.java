package com.shecan.service;

import com.shecan.model.ServiceProduct;
import com.shecan.repository.ProductRepository;
import java.util.List;

public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(ServiceProduct product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        repository.save(product);
    }

    public ServiceProduct findById(String id) {
        return repository.findById(id);
    }

    public List<ServiceProduct> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void bulkImport(List<ServiceProduct> products) {
        System.out.println("\n  Starting bulk import with " + products.size() + " products...");
        int saved = 0;
        for (ServiceProduct product : products) {
            if (product.getId() == null || product.getId().trim().isEmpty()) {
                throw new IllegalArgumentException("Product ID cannot be blank: " + product);
            }
            if (product.getPrice() < 0) {
                throw new IllegalArgumentException("Price cannot be negative for product: " + product.getId());
            }
            if (product.getStock() < 0) {
                throw new IllegalArgumentException("Stock cannot be negative for product: " + product.getId());
            }
            repository.save(product);
            saved++;
        }
        System.out.println("  Bulk import completed. Saved " + saved + " products.");
    }
}