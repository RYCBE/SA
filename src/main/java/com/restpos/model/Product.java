package com.restpos.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TBL_PRODUCTS")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prd_category")
    private String category;

    @Column(name = "prd_name")
    private String name;

    @Column(name = "prd_price")
    private double price;

    @Column(name = "prd_image")
    private String image;

    public Product() {
        super();
    }
    public Product(String category, String name, double price) {
        this(category, name, price, "/assets/images/placeholder.jpg");
    }
    public Product(Long id, String name, double price) {
        this(id, name, price, "/assets/images/items/"+String.valueOf(id)+".jpg");
    }

    public Product(Long id,String category, String name, double price) {
        this(id, category , name, price, "/assets/images/items/"+String.valueOf(id)+".jpg");
    }
    public Product(String category, String name, double price, String image) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }
    public Product(Long id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public Product(Long id,String category, String name, double price, String image) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString(){
        return id+" "+category+" "+price+" "+name;
    }
}
