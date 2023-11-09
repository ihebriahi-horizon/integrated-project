package com.example.back.service;

import com.example.back.entity.Product;
import com.example.back.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public Page<Product> getProducts(int page, int size) {
		if (size <= 0) {
			size = 5;
		}
		return productRepo.findAllByOrderByProductAddDateDesc(PageRequest.of(page, size));
	}

	public Product getProduct(int productId) {
		return productRepo.findById(productId).get();
	}

	public void addProduct(Product product) {
		productRepo.save(product);
	}

	public void updateProduct(int productId, Product updatedProduct) {
		Optional<Product> existingProduct = productRepo.findById(productId);

		if (existingProduct.isPresent()) {
			Product product = existingProduct.get();
			product.setProductName(updatedProduct.getProductName());
			product.setProductPrice(updatedProduct.getProductPrice());
			product.setProductFor(updatedProduct.getProductFor());
			product.setProductType(updatedProduct.getProductType());
			product.setProductColor(updatedProduct.getProductColor());
			product.setProductImagePath(updatedProduct.getProductImagePath());
			productRepo.save(product);
		}
	}

	public void deleteProduct(int productId) {
		productRepo.deleteById(productId);
	}

	public Page<Product> getProductsByGender(String gender, String name,
			org.springframework.data.domain.Pageable pageable, double productMinPrice, double productMaxPrice,
			Set<String> colors, String type) {

		boolean colorsNotEmpty = false;

		if (colors != null && colors.size() != 0) {
			colorsNotEmpty = true;
		}

		return productRepo.filterProducts(gender, name, productMinPrice, productMaxPrice, colorsNotEmpty, colors, type,
				pageable);
		/*
		 * if (productMinPrice != 0 && productMaxPrice != 0) {
		 * 
		 * return productRepo.
		 * findByProductForAndProductNameContainingAndProductPriceBetweenOrderByProductAddDateDesc
		 * (gender, name, productMinPrice, productMaxPrice, pageable);
		 * 
		 * } else if (productMinPrice != 0) {
		 * 
		 * return productRepo.
		 * findByProductForAndProductNameContainingAndProductPriceGreaterThanOrderByProductAddDateDesc
		 * (gender, name, productMinPrice, pageable);
		 * 
		 * } else if (productMaxPrice != 0) {
		 * 
		 * return productRepo.
		 * findByProductForAndProductNameContainingAndProductPriceLessThanOrderByProductAddDateDesc
		 * (gender, name, productMaxPrice, pageable);
		 * 
		 * } else { return
		 * productRepo.findByProductForAndProductNameContainingOrderByProductAddDateDesc
		 * (gender,name, pageable); }
		 */

	}

	/*
	 * public Page<Product> getProductsByName(String name,
	 * org.springframework.data.domain.Pageable pageable, double productMinPrice,
	 * double productMaxPrice) {
	 * 
	 * if (productMinPrice != 0 && productMaxPrice != 0) {
	 * 
	 * return productRepo.
	 * findByProductNameContainingAndProductPriceBetweenOrderByProductAddDateDesc(
	 * name, productMinPrice, productMaxPrice, pageable);
	 * 
	 * } else if (productMinPrice != 0) {
	 * 
	 * return productRepo.
	 * findByProductNameContainingAndProductPriceGreaterThanOrderByProductAddDateDesc(
	 * name, productMinPrice, pageable);
	 * 
	 * } else if (productMaxPrice != 0) {
	 * 
	 * return productRepo.
	 * findByProductNameContainingAndProductPriceLessThanOrderByProductAddDateDesc(
	 * name, productMaxPrice, pageable);
	 * 
	 * } else { return
	 * productRepo.findByProductNameContainingOrderByProductAddDateDesc(name,
	 * pageable); }
	 * 
	 * }
	 */

	public Set<String> getProductTypes() {
		return productRepo.getProductTypes();
	}

	public Set<String> getProductColors() {
		return productRepo.getProductColors();
	}

	public Double getMinProductPrice() {
		return productRepo.getMinProductPrice();
	}

	public Double getMaxProductPrice() {
		return productRepo.getMaxProductPrice();
	}

}