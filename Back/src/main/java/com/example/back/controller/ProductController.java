package com.example.back.controller;

import com.example.back.entity.PageResponse;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public Page<Product> getProducts(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "0") int size) {
		return productService.getProducts(page, size);
	}

	@GetMapping("search")
	public PageResponse getProducts(@RequestParam(name = "gender", required = false, defaultValue = "") String gender,
			@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "page", required = true, defaultValue = "0") int page,
			@RequestParam(name = "size", required = true, defaultValue = "12") int size,
			@RequestParam(name = "minPrice", required = false, defaultValue = "0") double productMinPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = "0") double productMaxPrice,
			@RequestParam(name = "colors", required = false) Set<String> colors,
			@RequestParam(name = "type", required = false, defaultValue = "") String type) {
		Page<Product> dataPage;
		/*
		 * if(name.equals("")) { dataPage = productService.getProductsByGender(gender,
		 * name, PageRequest.of(page, size), productMinPrice, productMaxPrice); } else {
		 * dataPage = productService.getProductsByName( name, PageRequest.of(page,
		 * size), productMinPrice, productMaxPrice); }
		 */
		if(gender==null) {
			System.out.println("gender is null");
		}
		dataPage = productService.getProductsByGender(gender, name, PageRequest.of(page, size), productMinPrice,
				productMaxPrice, colors, type);
		return new PageResponse(dataPage.getContent(), dataPage.getTotalPages(), dataPage.getTotalElements());

	}

	@GetMapping("{productId}")
	public Product getProducts(@PathVariable int productId) {
		return productService.getProduct(productId);
	}

	@PostMapping("")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@PutMapping("{productId}")
	public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
		productService.updateProduct(productId, product);
	}

	@DeleteMapping("{productId}")
	public void deleteProduct(@PathVariable int productId) {
		productService.deleteProduct(productId);
	}

	@GetMapping("types")
	public Set<String> getTypes() {
		return productService.getProductTypes();
	}

	@GetMapping("colors")
	public Set<String> getColors() {
		return productService.getProductColors();
	}
 
	@GetMapping("price/min")
	public Double getMinProductPrice() {
		return productService.getMinProductPrice();
	}

	@GetMapping("price/max")
	public Double getMaxProductPrice() {
		return productService.getMaxProductPrice();
	}
}