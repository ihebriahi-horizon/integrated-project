package com.example.back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import com.example.back.controller.ProductController;
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
}
