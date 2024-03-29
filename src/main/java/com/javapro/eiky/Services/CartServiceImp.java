package com.javapro.eiky.Services;

import com.javapro.eiky.Models.cart.Cart;
import com.javapro.eiky.Models.cart.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService{
    private CartRepository cartRepository;

    public CartServiceImp(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Iterable<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new Error());
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
