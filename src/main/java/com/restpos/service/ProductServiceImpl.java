package com.restpos.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.restpos.model.Product;
import com.restpos.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Iterable<Product> products() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> randomProduct() {
        return productRepository.findById(Long.valueOf(new Random().nextLong()));
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

}
