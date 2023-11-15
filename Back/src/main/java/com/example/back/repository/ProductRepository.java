package com.example.back.repository;

import com.example.back.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Page<Product> findAllByOrderByProductAddDateDesc(Pageable pageable);

	/*
	 * @Query("SELECT p FROM product p ORDER BY p.productAddDate DESC")
	 * Page<Product> findAllByOrderByProductAddDateDesc();
	 */

	/*
	 * @Query("SELECT p FROM product p WHERE ( CASE WHEN ?1 <> '' THEN p.productFor = ?1 ELSE TRUE END OR p.productFor='Both' ) AND p.productName LIKE %?2% ORDER BY productAddDate DESC   "
	 * ) Page<Product>
	 * findByProductForAndProductNameContainingOrderByProductAddDateDesc( String
	 * gender, String name, Pageable pageable);
	 */
	/*
	 * @Query("SELECT p FROM product p WHERE ( CASE WHEN ?1 <> '' THEN p.productFor = ?1 ELSE TRUE END OR p.productFor='Both' ) AND p.productName LIKE %?2% AND p.productPrice BETWEEN ?3 AND ?4 ORDER BY productAddDate DESC   "
	 * ) Page<Product>
	 * findByProductForAndProductNameContainingAndProductPriceBetweenOrderByProductAddDateDesc(
	 * String gender, String name, double productMinPrice, double productMaxPrice,
	 * Pageable pageable);
	 * 
	 */

	@Query("SELECT p " + "FROM product p " + "WHERE "
			+ "CASE WHEN ?1 <> '' THEN p.productFor = ?1 OR p.productFor = 'Both' ELSE TRUE END "
			+ "AND p.productName LIKE %?2% "
			+ "AND CASE WHEN ?3 <> 0 THEN  p.productPrice >= ?3 ELSE TRUE END "
			+ "AND CASE WHEN ?4 <> 0 THEN  p.productPrice <= ?4 ELSE TRUE END "
			+ "AND CASE WHEN ?5=true THEN  p.productColor IN ?6 ELSE TRUE END "
			+ "AND CASE WHEN ?7 <> '' THEN  p.productType = ?7 ELSE TRUE END "
			+ "ORDER BY productAddDate DESC   ")

	Page<Product> filterProducts(String gender,
			String name, double productMinPrice, double productMaxPrice, boolean colorsNotEmpty, Set<String> colors,
			String type, Pageable pageable);
	/*
	 * @Query("SELECT p FROM product p WHERE ( CASE WHEN ?1 <> '' THEN p.productFor = ?1 ELSE TRUE END OR p.productFor='Both' ) AND p.productName LIKE %?2% AND p.productPrice >= ?3 ORDER BY productAddDate DESC   "
	 * ) Page<Product>
	 * findByProductForAndProductNameContainingAndProductPriceGreaterThanOrderByProductAddDateDesc(
	 * String gender, String name, double productMinPrice, Pageable pageable);
	 * 
	 * @Query("SELECT p FROM product p WHERE ( CASE WHEN ?1 <> '' THEN p.productFor = ?1 ELSE TRUE END OR p.productFor = 'Both' ) AND p.productName LIKE %?2% AND p.productPrice <= ?3 ORDER BY productAddDate DESC   "
	 * ) Page<Product>
	 * findByProductForAndProductNameContainingAndProductPriceLessThanOrderByProductAddDateDesc(
	 * String gender, String name, double productMinPrice, Pageable pageable);
	 */

	@Query("SELECT DISTINCT p.productType FROM product p WHERE p.productColor!=''")
	Set<String> getProductTypes();

	@Query("SELECT DISTINCT p.productColor FROM product p WHERE p.productColor!=''")
	Set<String> getProductColors();

	@Query("SELECT MIN(p.productPrice) FROM product p ")
	Double getMinProductPrice();

	@Query("SELECT MAX(p.productPrice) FROM product p ")
	Double getMaxProductPrice();
}