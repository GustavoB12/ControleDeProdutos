package br.com.compass.ControleDeProdutos.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.ControleDeProdutos.controller.dto.ProductDto;
import br.com.compass.ControleDeProdutos.controller.form.ProductForm;
import br.com.compass.ControleDeProdutos.model.Product;
import br.com.compass.ControleDeProdutos.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	@Cacheable(value = "listaDeProdutos")
	public Page<ProductDto> list(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		Page<Product> produtos = productRepository.findAll(paginacao);
		return ProductDto.converter(produtos);
	}

	@PostMapping
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder){
		
		Product product = form.converter();
		productRepository.save(product);

		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
