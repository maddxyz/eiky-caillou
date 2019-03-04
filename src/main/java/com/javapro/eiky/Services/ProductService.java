package com.javapro.eiky.Services;

import com.javapro.eiky.Models.product.Product;

public interface ProductService {
    public abstract Product getProduct(String id);
    public abstract Product saveProduct(Product product);
    public abstract void deleteProduct(Long id);
    public abstract Iterable<Product> getAllProducts();
}
