package br.com.compass.ControleDeProdutos.controller.dto;

import org.springframework.data.domain.Page;

import br.com.compass.ControleDeProdutos.model.Product;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public static Page<ProductDto> converter(Page<Product> produtos) {
		return produtos.map(ProductDto::new);
	}

}
