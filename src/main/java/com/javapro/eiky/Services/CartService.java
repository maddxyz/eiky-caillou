package com.javapro.eiky.Services;

import com.javapro.eiky.Models.Cart;

public interface CartService {
    public abstract Cart getCart(Long id);
    public abstract Cart saveCart(Cart cart);
    public abstract void deleteCart(Long id);
    public abstract Iterable<Cart> getAllCarts();
}
