package br.com.compass.ControleDeProdutos.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.ControleDeProdutos.controller.dto.ProductDto;
import br.com.compass.ControleDeProdutos.controller.form.ProductForm;
import br.com.compass.ControleDeProdutos.controller.form.UpdateProductForm;
import br.com.compass.ControleDeProdutos.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@Api(value="API REST Produtos")
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@ApiOperation(value="Retorna uma lista de Produtos")
	@GetMapping
	@Cacheable(value = "listaDeProdutos")
	public Page<ProductDto> list(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return productService.list(paginacao);
	}

	@PostMapping
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		return productService.create(form, uriBuilder);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return productService.delete(id);
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeProdutos", allEntries = true)
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid UpdateProductForm form) {
		return productService.update(id, form);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
		return productService.findById(id);
	}

	@GetMapping("/search")
	@Cacheable(value = "listaDeProdutos")
	public Page<ProductDto> search(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@Param("q") String q, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice) {
		return productService.search(paginacao, q, minPrice, maxPrice);
	}

}