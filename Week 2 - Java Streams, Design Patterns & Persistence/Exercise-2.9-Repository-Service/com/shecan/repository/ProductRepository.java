package com.shecan.repository;

import com.shecan.model.ServiceProduct;
import java.util.List;

public interface ProductRepository {
    void save(ServiceProduct product);
    ServiceProduct findById(String id);
    List<ServiceProduct> findAll();
    void deleteById(String id);
}