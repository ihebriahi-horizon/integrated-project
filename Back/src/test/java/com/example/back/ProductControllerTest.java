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
    @Test
    public void testGetProduct() {
        int productId = 1;
        Product expectedProduct = mock(Product.class);
        when(productService.getProduct(productId)).thenReturn(expectedProduct);

        Product actualProduct = productController.getProducts(productId);

        assertEquals(expectedProduct, actualProduct);
        verify(productService).getProduct(productId);
    }

    @Test
    public void testAddProduct() {
        Product product = mock(Product.class);

        productController.addProduct(product);

        verify(productService).addProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        int productId = 1;
        Product product = mock(Product.class);

        productController.updateProduct(productId, product);

        verify(productService).updateProduct(productId, product);
    }

    @Test
    public void testDeleteProduct() {
        int productId = 1;

        productController.deleteProduct(productId);

        verify(productService).deleteProduct(productId);
    }
    @Test
    public void testGetTypes() {
        Set<String> expectedTypes = new HashSet<>(Collections.singletonList("t-shirt"));
        when(productService.getProductTypes()).thenReturn(expectedTypes);

        Set<String> actualTypes = productController.getTypes();

        assertEquals(expectedTypes, actualTypes);
        verify(productService).getProductTypes();
    }

    @Test
    public void testGetColors() {
        Set<String> expectedColors = new HashSet<>(Collections.singletonList("red"));
        when(productService.getProductColors()).thenReturn(expectedColors);

        Set<String> actualColors = productController.getColors();

        assertEquals(expectedColors, actualColors);
        verify(productService).getProductColors();
    }

    @Test
    public void testGetMinProductPrice() {
        Double expectedMinPrice = 0.0;
        when(productService.getMinProductPrice()).thenReturn(expectedMinPrice);

        Double actualMinPrice = productController.getMinProductPrice();

        assertEquals(expectedMinPrice, actualMinPrice);
        verify(productService).getMinProductPrice();
    }

    @Test
    public void testGetMaxProductPrice() {
        Double expectedMaxPrice = 100.0;
        when(productService.getMaxProductPrice()).thenReturn(expectedMaxPrice);

        Double actualMaxPrice = productController.getMaxProductPrice();

        assertEquals(expectedMaxPrice, actualMaxPrice);
        verify(productService).getMaxProductPrice();
    }
}
