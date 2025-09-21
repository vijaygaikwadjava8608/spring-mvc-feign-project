package com.grocery.product_service.service;

import com.grocery.product_service.entity.Product;
import com.grocery.product_service.repository.ProductRepository;
import jdk.jfr.Category;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    Logger log = org.slf4j.LoggerFactory.getLogger(ProductService.class);
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    ProductService(ProductRepository productRepository) {
        log.info("ProductService initialized");
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.debug("Found {} products", products.size());
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
         log.info("Creating new product: {}", product.getName());
       if(!validateCategoryId(product.getCategoryId() )) {
           log.error("Invalid Category ID: {}", product.getCategoryId());
           throw new RuntimeException("Invalid Category ID");
       }
        log.debug("Category ID: {} is valid", product.getCategoryId());
        return productRepository.save(product);

    }
    public boolean validateCategoryId(Long categoryId) {
        // Implement the logic to validate the category ID
        try {
            log.debug("Validating Category ID: {}", categoryId);
            ResponseEntity<Void> response = restTemplate.getForEntity("http://category-service130925.azurewebsites.net/api/categories/" + categoryId, Void.class);
            log.debug("Category ID: {} validation response status: {}", categoryId, response.getStatusCode());
            return response.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }



    public void deleteProduct(Long productId) {
        log.debug("Deleting product with ID: {}", productId);
         if(!productRepository.existsById(productId)) {
             log.error("Product with ID: {} does not exist", productId);
             throw new RuntimeException("Product not found");
         }
         log.debug("Product with ID: {} exists. Proceeding with deletion.", productId);
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        log.info(" In Product Service : getCategory  Id :{} ",  categoryId);
        if(!validateCategoryId(categoryId)) {
            log.error("Invalid Category ID: {}", categoryId);
                throw new RuntimeException("Invalid Category ID");
            }
        log.debug("Category ID: {} is valid. Fetching products.", categoryId);
        return productRepository.findByCategoryId(categoryId);
    }



}
