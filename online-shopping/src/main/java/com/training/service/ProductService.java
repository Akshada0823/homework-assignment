package com.training.service;

import com.training.model.Product;
import com.training.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(String id) throws Exception {
        Optional<Product> product;
        try {
           product = productRepository.findById(id);
           if(product != null) {
               return product;
           }else{
               throw new Exception("Product not found");
           }
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }

    }
}