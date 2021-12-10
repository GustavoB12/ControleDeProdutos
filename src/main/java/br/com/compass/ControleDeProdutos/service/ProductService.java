package br.com.compass.ControleDeProdutos.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.ControleDeProdutos.controller.dto.ProductDto;
import br.com.compass.ControleDeProdutos.controller.form.ProductForm;
import br.com.compass.ControleDeProdutos.controller.form.UpdateProductForm;
import br.com.compass.ControleDeProdutos.model.Product;
import br.com.compass.ControleDeProdutos.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<ProductDto> list(Pageable paginacao) {
		Page<Product> produtos = productRepository.findAll(paginacao);
		return ProductDto.converter(produtos);
	}

	public ResponseEntity<ProductDto> create(ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.converter();
		productRepository.save(product);

		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));

	}

	public ResponseEntity<ProductDto> delete(Long id) {

		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<ProductDto> update(Long id, UpdateProductForm form) {

		Product product = form.atualizar(id, productRepository);
		return ResponseEntity.ok(new ProductDto(product));
	}

	public ResponseEntity<ProductDto> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return ResponseEntity.ok(new ProductDto(product.get()));
	}

	public Page<ProductDto> search(Pageable paginacao, String q, Double minPrice, Double maxPrice) {

		Page<Product> produtos = productRepository.search(q, minPrice, maxPrice, paginacao);
		return ProductDto.converter(produtos);
	}

}
