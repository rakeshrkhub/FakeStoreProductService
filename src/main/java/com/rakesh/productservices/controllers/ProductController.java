package com.rakesh.productservices.controllers;

import com.rakesh.productservices.models.Product;
import com.rakesh.productservices.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
}
