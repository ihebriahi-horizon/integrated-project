package com.example.back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.back.controller.ProductController;
import com.example.back.entity.PageResponse;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;

public class ProductControllerTest {
    private ProductController productController;
    @Mock
    private ProductService productService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }
     @Test
    public void testGetProducts() {
        int page = 0;
        int size = 0;
        Page<Product> expectedPage = mock(Page.class);
        when(productService.getProducts(page, size)).thenReturn(expectedPage);

        Page<Product> actualPage = productController.getProducts(page, size);

        assertEquals(expectedPage, actualPage);
        verify(productService).getProducts(page, size);
    }
    @Test
    public void testGetProductsWithSearchParams() {
        String gender = "male";
        String name = "shirt";
        int page = 0;
        int size = 12;
        double minPrice = 0;
        double maxPrice = 0;
        Set<String> colors = new HashSet<>(Collections.singletonList("red"));
        String type = "t-shirt";
        Page<Product> expectedPage = mock(Page.class);
        when(productService.getProductsByGender(gender, name, PageRequest.of(page, size), minPrice, maxPrice, colors, type)).thenReturn(expectedPage);

        PageResponse actualPageResponse = productController.getProducts(gender, name, page, size, minPrice, maxPrice, colors, type);

        assertEquals(expectedPage.getContent(), actualPageResponse.getContent());
        assertEquals(expectedPage.getTotalPages(), actualPageResponse.getTotalPages());
        assertEquals(expectedPage.getTotalElements(), actualPageResponse.getTotalElements());
        verify(productService).getProductsByGender(gender, name, PageRequest.of(page, size), minPrice, maxPrice, colors, type);
    }
}
