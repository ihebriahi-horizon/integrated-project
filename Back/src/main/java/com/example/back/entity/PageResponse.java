package com.example.back.entity;

import java.util.List;

public class PageResponse {

	private List<Product> content;
	private int totalPages;
	private long totalElements;

	public PageResponse(List<Product> content, int totalPages, Long totalElements) {
		this.content = content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public List<Product> getContent() {
		return content;
	}

	public void setContent(List<Product> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
