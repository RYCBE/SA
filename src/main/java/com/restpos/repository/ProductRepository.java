package com.restpos.repository;

import org.springframework.data.repository.CrudRepository;

import com.restpos.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
