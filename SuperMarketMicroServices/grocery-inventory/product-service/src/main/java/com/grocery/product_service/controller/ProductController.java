package com.grocery.product_service.controller;

import com.grocery.product_service.entity.Product;
import com.grocery.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
private final ProductService productService;

    Logger logger = org.slf4j.LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("in Product Service Controller - Fetching all products");

        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        logger.info("in Product Service Controller - Creating new product: {}", product.getName());
        Product createdProduct = productService.createProduct(product);
        logger.debug("Product created with ID: {}", createdProduct.getId());
        return ResponseEntity.status(201).body(createdProduct);

    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        logger.info("in Product Service Controller - Deleting product with ID: {}", productId);
        productService.deleteProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        logger.info("in Product Service Controller - Fetching products for category ID: {}", categoryId);
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/test")
    public String test() {
        logger.info("in Product Service Controller - Test endpoint hit");
        return "Product Service is up and running!";
    }


    


}
