package br.com.compass.ControleDeProdutos.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.compass.ControleDeProdutos.model.Product;

public class ProductForm {
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String name;
	@NotNull @NotEmpty @Length(min = 10, max = 1000)
	private String description;
	@NotNull 
	private Double price;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Product converter() {
		return new Product(name, description, price);
	}
	
	
}
