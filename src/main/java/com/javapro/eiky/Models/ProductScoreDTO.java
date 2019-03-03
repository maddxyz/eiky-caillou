package com.javapro.eiky.Models;

import lombok.Data;

@Data
public class ProductScoreDTO {
    private String id;
    private String code;
    private String product_name;
    private long nutritional_score;
}
