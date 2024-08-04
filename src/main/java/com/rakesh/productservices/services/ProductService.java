package com.rakesh.productservices.services;

import com.rakesh.productservices.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
}
