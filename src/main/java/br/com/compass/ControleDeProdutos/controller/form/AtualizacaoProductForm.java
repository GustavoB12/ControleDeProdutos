package br.com.compass.ControleDeProdutos.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.compass.ControleDeProdutos.model.Product;
import br.com.compass.ControleDeProdutos.repository.ProductRepository;

public class AtualizacaoProductForm {

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 100)
	private String name;
	@NotNull
	@NotEmpty
	@Length(min = 10, max = 1000)
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

	public Product atualizar(Long id, ProductRepository repository) {
		Product product = repository.getById(id);

		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);

		return product;
	}

}
