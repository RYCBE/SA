package com.restpos.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.restpos.model.Item;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restpos.model.Product;
import com.restpos.service.ProductService;

@RestController
public class ProductController {

	private final ProductService productService;

	private final double tax = 0.15;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<CollectionModel<EntityModel<Product>>> allProducts() {
		List<EntityModel<Product>> products = StreamSupport.stream(productService.products().spliterator(), false)
				.map(product -> EntityModel.of(product,
						linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel(),
						linkTo(methodOn(ProductController.class).allProducts()).withRel("products")))
				.collect(Collectors.toList());

		return ResponseEntity.ok( //
				CollectionModel.of(products,
						linkTo(methodOn(ProductController.class).allProducts()).withSelfRel()));
	}

	@GetMapping("/products/{id}")
	ResponseEntity<EntityModel<Product>> getProduct(@PathVariable Long id) {

		return productService.getProduct(id)
				.map(product -> EntityModel.of(product,
						linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel(),
						linkTo(methodOn(ProductController.class).allProducts()).withRel("products")))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/products")
	ResponseEntity<?> newProduct(@RequestParam String category, @RequestParam String name, @RequestParam double price){
		try {
			Product product = new Product(category,name,price);
			Product product1 = productService.save(product);

			EntityModel<Product> productEntityModel = EntityModel.of(product1,
					linkTo(methodOn(ProductController.class).getProduct(product1.getId())).withSelfRel());
			return ResponseEntity.created(
					new URI(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).getHref())
			).body(productEntityModel);
		}catch (URISyntaxException e){
			return ResponseEntity.badRequest().body("Unable to create Product ");
		}
	}

	@GetMapping("/tax")
	public String getTax(){
		return String.valueOf(this.tax);
	}

}
