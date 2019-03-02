package com.javapro.eiky.Controllers;

import com.javapro.eiky.APIClient;
import com.javapro.eiky.Models.Barcode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {

    private final APIClient apiClient;

    public ProductController(APIClient apiClient) {
        this.apiClient = apiClient;
    }


    @GetMapping("/product/{barcode}")
    @ResponseBody
    public Barcode fetchProduct(@PathVariable String barcode) {
        return this.apiClient.fetchProduct(barcode);
    }

    @GetMapping("/product/{barcode}/score")
    @ResponseBody
    public long fetchProductNutritionalScore(@PathVariable String barcode) {
        return this.apiClient.fetchProduct(barcode).getProduct().nutritionalScore();
    }

    @GetMapping("/product/{barcode}/synthesis")
    @ResponseBody
    public String fetchProductSynthesis(@PathVariable String barcode) {
        return this.apiClient.fetchProduct(barcode).getProduct().Synthesis();
    }
}
