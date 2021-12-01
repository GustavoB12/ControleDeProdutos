package br.com.compass.ControleDeProdutos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.ControleDeProdutos.controller.dto.ProductDto;
import br.com.compass.ControleDeProdutos.model.Product;
import br.com.compass.ControleDeProdutos.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository repository;

	@GetMapping
	@Cacheable(value = "listaDeProdutos")
	public Page<ProductDto> lista(
			@PageableDefault(sort = "name", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Product> produtos = repository.findAll(paginacao);		
		return ProductDto.converter(produtos);
	}
}
