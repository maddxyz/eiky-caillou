package com.javapro.eiky.Controllers;

import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.Barcode;
import com.javapro.eiky.Models.Product;
import com.javapro.eiky.Models.ProductScoreDTO;
import com.javapro.eiky.Models.ProductSynthesisDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;



@RestController
public class ProductController {

    private final APIClient apiClient;

    public ProductController(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping("/product/{barcode}")
    public Barcode fetchProduct(@PathVariable String barcode) {
        return this.apiClient.fetchProduct(barcode);
    }

    @GetMapping("/product/{barcode}/score")
    public ProductScoreDTO fetchProductNutritionalScore(@PathVariable String barcode) {
        Product p = this.apiClient.fetchProduct(barcode).getProduct();
        p.calculateNutritionalScore();
        ModelMapper modelMapper = new ModelMapper();
        ProductScoreDTO pdto = modelMapper.map(p, ProductScoreDTO.class);
        return pdto;
    }

    @GetMapping("/product/{barcode}/synthesis")
    public ProductSynthesisDTO fetchProductSynthesis(@PathVariable String barcode) {
        Product p = this.apiClient.fetchProduct(barcode).getProduct();
        p.determineFlaws();
        p.determineQualities();
        ModelMapper modelMapper = new ModelMapper();
        ProductSynthesisDTO pdto = modelMapper.map(p, ProductSynthesisDTO.class);
        return pdto;
    }
}
