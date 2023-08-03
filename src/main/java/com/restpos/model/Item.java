package com.restpos.model;

public class Item {
    private Product product;
    private int quantity;

    public Item(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return product.toString() +"\t" + quantity;
    }
}
