package com.training.controller;/*
 * (c) Copyright 2016 Swisscom AG
 * All Rights Reserved.
 */


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.model.Product;
import com.training.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("mock")
 class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    public void createProduct_success() throws Exception{
        Product productmock = new Product("1","Test Product","This is a test product",12.99);


        String json= """
                {"id":"1","name":"Test Product","description":"This is a test product","price":12.99}""";
        when((productService).createProduct(productmock)).thenReturn(productmock);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                 .andDo(print())
                .andExpect(status().is2xxSuccessful());
                 verify(productService).createProduct(productmock);

    }
    @Test
    public void getProduct_success() throws Exception{
        Product product = new Product("1","Test Product","This is a test product",12.99);
        String json= """
                {"id":"1","name":"Test Product","description":"This is a test product","price":12.99}""";
        when((productService).getProduct("1")).thenReturn(Optional.of(product));
        mockMvc.perform(
                        get("/products/1")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(json));

        verify(productService).getProduct("1");
    }
    @Test
    public void getAllProducts_success() throws Exception{
        List<Product> productmock = Arrays.asList(
                Product.builder().id("123").name("test").description("testing").price(8.67).build(),
                Product.builder().id("1234").name("test1").description("testing1").price(10.99).build());
        String mockResp= """
                [{"id":"123","name":"test","description":"testing","price":8.67},{"id":"1234","name":"test1","description":"testing1","price":10.99}]""";
        when((productService).getAllProducts()).thenReturn(productmock);
        mockMvc.perform(
                        get("/products")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(mockResp));

        verify(productService).getAllProducts();
    }
}

