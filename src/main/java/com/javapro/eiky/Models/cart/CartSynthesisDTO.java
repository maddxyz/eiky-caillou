package com.javapro.eiky.Models.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartSynthesisDTO {
    private Long id;
    private List<String> cartQualities;
    private List<String> cartFlaws;
}
