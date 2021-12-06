package br.com.compass.ControleDeProdutos.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SearchForm {
	
	
	@NotNull
	@NotEmpty
	private String q;
	@NotNull
	@NotEmpty
	private Double minPrice;
	@NotNull
	@NotEmpty
	private Double maxPrice;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Double getMinTotal() {
		return minPrice;
	}

	public void setMinTotal(Double minTotal) {
		this.minPrice = minTotal;
	}

	public Double getMaxTotal() {
		return maxPrice;
	}

	public void setMaxTotal(Double maxTotal) {
		this.maxPrice = maxTotal;
	}

}
