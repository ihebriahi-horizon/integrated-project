package com.example.back;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.back.controller.ProductController;
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
}
