package com.restpos.service;

import java.util.Optional;

import com.restpos.model.Product;

public interface ProductService {

    public Iterable<Product> products();

    public Optional<Product> getProduct(Long id);

    public Optional<Product> randomProduct();

    public Product save(Product product);
}
