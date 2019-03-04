package com.javapro.eiky.Models.others;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javapro.eiky.Models.product.Product;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Barcode {
    @Id
    private String code;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private float status;


    // Getter Methods

    public String getCode() {
        return code;
    }

    public Product getProduct() {
        return product;
    }

    public float getStatus() {
        return status;
    }

    // Setter Methods

    public void setCode(String code) {
        this.code = code;
    }

    public void setProduct(Product productObject) {
        this.product = productObject;
    }

    public void setStatus(float status) {
        this.status = status;
    }
}
