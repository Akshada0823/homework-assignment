package com.training.service;

import com.training.model.Product;
import com.training.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @InjectMocks
    ProductService service;

    @Test
    void getProduct_success() throws Exception {
        Product productmock = Product
                .builder()
                .id("123")
                .name("test")
                .description("testing")
                .price(8.67)
                .build();
        when(repository.findById("123")).thenReturn(Optional.of(productmock));
        Optional<Product> productRes =  service.getProduct("123");
        Assertions.assertThat(productRes).isNotNull();
        //verify(service).("123");
    }
    @Test
    void getAllProducts_success() {
        List<Product> productmock = Arrays.asList(
                Product.builder().id("123").name("test").description("testing").price(8.67).build(),
                Product.builder().id("1234").name("test1").description("testing1").price(10.99).build());
        when(repository.findAll()).thenReturn(productmock);
        List<Product> productRes =  service.getAllProducts();
        Assertions.assertThat(productRes).isNotNull();

    }
    @Test
    void createProduct_success() {
        Product productmock = Product
                .builder()
                .id("123")
                .name("test")
                .description("testing")
                .price(8.67)
                .build();
        when(repository.save(productmock)).thenReturn(productmock);
         Product product = service.createProduct(productmock);
        Assertions.assertThat(product).isNotNull();
    }

    @Test
    void getProduct_failure() throws Exception {
        when(repository.findById("123")).thenReturn(null);
        catchThrowableOfType(() -> service.getProduct("123"),Exception.class);
    }
}