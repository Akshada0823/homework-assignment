package com.training.controller;

import com.training.model.Product;
import com.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

        private final ProductService productService;

        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @PostMapping()
        public ResponseEntity<Product> createProduct(@RequestBody Product product) {
            Product createdProduct = productService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

       @GetMapping("/{id}")
       public ResponseEntity <Optional<Product>> getProduct(@PathVariable String id) throws Exception {
            Optional<Product> product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
    }
    }
