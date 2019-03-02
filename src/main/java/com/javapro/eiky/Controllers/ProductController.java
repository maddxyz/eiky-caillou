package com.javapro.eiky.Controllers;

import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.Barcode;
import com.javapro.eiky.Models.Product;
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


    @GetMapping("/product/{barcode}")
    public Barcode fetchProduct(@PathVariable String barcode) {
        return this.apiClient.fetchProduct(barcode);
    }

    @GetMapping("/product/{barcode}/score")
    public HashMap<String, Long> fetchProductNutritionalScore(@PathVariable String barcode) {
        HashMap<String, Long> map = new HashMap<>();
        Product p = this.apiClient.fetchProduct(barcode).getProduct();
        map.put("product", Long.parseLong((p.getCode())));
        map.put("nutritional_score", (p.nutritionalScore()));
        return map;
    }

    @GetMapping("/product/{barcode}/synthesis")
    public HashMap<String, String> fetchProductSynthesis(@PathVariable String barcode) {
        HashMap<String, String> map = new HashMap<>();
        Product p = this.apiClient.fetchProduct(barcode).getProduct();
        map.put("product", p.getCode());
        map.put("qualites",p.getQualities());
        map.put("defauts",p.getFlaws());
        return map;
    }
}
