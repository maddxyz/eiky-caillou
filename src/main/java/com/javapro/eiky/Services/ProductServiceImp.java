package com.javapro.eiky.Services;
import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.product.Product;
import com.javapro.eiky.Models.product.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImp implements ProductService{

    private ProductRepository productRepository;
    private APIClient apiClient;


    public ProductServiceImp(ProductRepository productRepository, APIClient apiClient) {
        this.productRepository = productRepository;
        this.apiClient = apiClient;
    }

    @Override
    public List<Product> getAllProducts() {
        return ( List<Product> ) productRepository.findAll();
    }

    @Override
    public Product getProduct(String barcode) {
        Product p = this.apiClient.fetchProduct(barcode).getProduct();
        return p;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }
}
