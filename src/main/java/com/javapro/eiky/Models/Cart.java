package com.javapro.eiky.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User usr;
    @OneToMany(mappedBy = "id")
    private List<Product> inProducts = new ArrayList<>();

    @Transient
    public long getTotalNutritionalScore(){
        long sum = 0 ;
        List<Product> inProducts = getInProducts();
        for (Product op : inProducts) {
            sum += op.nutritionalScore();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.inProducts.size();
    }
}
