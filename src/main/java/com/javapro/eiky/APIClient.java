package com.javapro.eiky;

import com.javapro.eiky.Models.others.Barcode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIClient {
    private static final String API_URL = "https://fr.openfoodfacts.org/api/v0/produit/{barcode}.json";

    private final RestTemplate restTemplate;

    public APIClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Barcode fetchProduct(String barcode){
        Barcode bc = this.restTemplate.getForObject(API_URL, Barcode.class, barcode);
        return bc.getStatus() == 1 ? bc : null;
    }
}
