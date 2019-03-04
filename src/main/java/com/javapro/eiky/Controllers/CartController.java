package com.javapro.eiky.Controllers;

import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.cart.Cart;
import com.javapro.eiky.Models.cart.CartScoreDTO;
import com.javapro.eiky.Models.cart.CartSynthesisDTO;
import com.javapro.eiky.Models.product.Product;
import com.javapro.eiky.Services.CartService;
import com.javapro.eiky.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CartController {

    private final APIClient apiClient;

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    public CartController(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/carts")
    private Iterable<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/cart/{id}")
    private Cart getCart(@PathVariable("id") Long id) {
        return cartService.getCart(id);
    }

    @DeleteMapping("/cart/{id}")
    private void deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
    }

    @PostMapping("/cart")
    private Long saveCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
        return cart.getId();
    }

    @GetMapping("/cart/{id}/products")
    private Iterable<Product> getAllProducts(@PathVariable("id") Long id) {
        return cartService.getCart(id).getInProducts();
    }

    @PostMapping("/cart/{id}/product/{barcode}")
    private Product addProduct(@PathVariable("id") Long id, @PathVariable("barcode") String barcode) {
        Cart c = cartService.getCart(id);
        Product p = productService.getProduct(barcode);
        p.setCart(c);
        return productService.saveProduct(p);
    }

    @DeleteMapping("/cart/{id}/product/{pid}")
    private void deleteProduct(@PathVariable("id") Long id, @PathVariable("pid") String pid) {
        Cart c = cartService.getCart(id);
        productService.deleteProduct(pid);
    }

    @GetMapping("/cart/{id}/score")
    public CartScoreDTO getTotalNutritionalScore(@PathVariable("id") Long id){
        long sum = 0 ;
        ModelMapper modelMapper = new ModelMapper();
        Cart c = cartService.getCart(id);
        CartScoreDTO cdto = modelMapper.map(c, CartScoreDTO.class);
//        for (String barcode : c.getProductsBarcode()){
//            sum += this.apiClient.fetchProduct(barcode).getProduct().nutritionalScore();
//        }
        for (Product product : c.getInProducts()){
            sum += product.nutritionalScore();
        }
        cdto.setNutritional_score(sum);

        return cdto;
    }

    @GetMapping("/cart/{id}/synthesis")
    public CartSynthesisDTO getCartSynthesis(@PathVariable("id") Long id){
        ModelMapper modelMapper = new ModelMapper();
        Cart c = cartService.getCart(id);
        CartSynthesisDTO cdto = modelMapper.map(c, CartSynthesisDTO.class);
        Set<String> qSet = new LinkedHashSet<>();
        Set<String> fSet = new LinkedHashSet<>();
        for (Product product : c.getInProducts()){
            qSet.addAll(product.getQualities());
            fSet.addAll(product.getFlaws());
        }
        System.out.println("qSet => " + qSet);
        System.out.println("fSet => " + fSet);
        cdto.setCartQualities(new ArrayList<String>(qSet));
        cdto.setCartFlaws(new ArrayList<String>(fSet));
        return cdto;
    }
}
