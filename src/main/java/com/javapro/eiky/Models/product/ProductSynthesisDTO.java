package com.javapro.eiky.Models.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductSynthesisDTO {
    private String id;
    private String code;
    private String product_name;
    private List<String> qualities;
    private List<String> flaws;
}
