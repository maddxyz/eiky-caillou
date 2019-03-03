package com.javapro.eiky.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Transient;

public class CartProduct {
    @EmbeddedId
    @JsonIgnore
    private CartProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    // default constructor

    public CartProduct(Cart cart, Product product, Integer quantity) {
        pk = new CartProductPK();
        pk.setCart(cart);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

}
