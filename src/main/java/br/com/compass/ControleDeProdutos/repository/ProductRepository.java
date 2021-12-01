package br.com.compass.ControleDeProdutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.ControleDeProdutos.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
