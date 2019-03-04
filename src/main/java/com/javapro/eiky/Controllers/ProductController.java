package com.javapro.eiky.Controllers;

import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.product.Product;
import com.javapro.eiky.Models.product.ProductScoreDTO;
import com.javapro.eiky.Models.product.ProductSynthesisDTO;
import com.javapro.eiky.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @GetMapping("/product/{barcode}")
    public Product fetchProduct(@PathVariable String barcode) {
        return productService.getProduct(barcode);
    }

    @GetMapping("/product/{barcode}/score")
    public ProductScoreDTO fetchProductNutritionalScore(@PathVariable String barcode) {
        Product p = productService.getProduct(barcode);
        p.calculateNutritionalScore();
        ModelMapper modelMapper = new ModelMapper();
        ProductScoreDTO pdto = modelMapper.map(p, ProductScoreDTO.class);
        return pdto;
    }

    @GetMapping("/product/{barcode}/synthesis")
    public ProductSynthesisDTO fetchProductSynthesis(@PathVariable String barcode) {
        Product p = productService.getProduct(barcode);
        p.determineFlaws();
        p.determineQualities();
        ModelMapper modelMapper = new ModelMapper();
        ProductSynthesisDTO pdto = modelMapper.map(p, ProductSynthesisDTO.class);
        return pdto;
    }

    @PostMapping("product/{barcode}/save")
    private Product saveProduct(@PathVariable("barcode") String barcode) {
        Product p = productService.getProduct(barcode);
        return productService.saveProduct(p);
    }
}
