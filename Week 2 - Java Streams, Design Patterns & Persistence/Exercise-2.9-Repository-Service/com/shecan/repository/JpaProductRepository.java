package com.shecan.repository;

import com.shecan.model.ServiceProduct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JpaProductRepository implements ProductRepository {
    private Map<String, ServiceProduct> database = new ConcurrentHashMap<>();

    @Override
    public void save(ServiceProduct product) {
        database.put(product.getId(), product);
        System.out.println("  [JPA] Saved: " + product.getId());
    }

    @Override
    public ServiceProduct findById(String id) {
        System.out.println("  [JPA] Found: " + id);
        return database.get(id);
    }

    @Override
    public List<ServiceProduct> findAll() {
        System.out.println("  [JPA] Found all: " + database.size() + " products");
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(String id) {
        database.remove(id);
        System.out.println("  [JPA] Deleted: " + id);
    }
}