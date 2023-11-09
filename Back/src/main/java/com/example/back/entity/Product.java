package com.example.back.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity(name = "product")
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_image_path")
	private String productImagePath;

	@Column(name = "product_price")
	private double productPrice;

	@Column(name = "product_for")
	private String productFor;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_color")
	private String productColor;
	
	@Column(name = "product_add_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable=false, updatable=false)
	private Timestamp productAddDate;

	public Product() {

	}

	public Product(int productId, String productName, String productImagePath, double productPrice, String productFor,
			String productType, String productColor, Timestamp productAddDate) {
		this.productId = productId;
		this.productName = productName;
		this.productImagePath = productImagePath;
		this.productPrice = productPrice;
		this.productFor = productFor;
		this.productType = productType;
		this.productColor = productColor;
		this.productAddDate = productAddDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductFor() {
		return productFor;
	}

	public void setProductFor(String productFor) {
		this.productFor = productFor;
	}

	public Timestamp getProductAddDate() {
		return productAddDate;
	}

	public void setProductAddDate(Timestamp productAddDate) {
		this.productAddDate = productAddDate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productImagePath="
				+ productImagePath + ", productPrice=" + productPrice + ", productFor=" + productFor + ", productType="
				+ productType + ", productColor=" + productColor + ", productAddDate=" + productAddDate + "]";
	}

	

}