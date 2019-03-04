package com.javapro.eiky.Models.cart;

import com.javapro.eiky.Models.user.User;
import com.javapro.eiky.Models.product.Product;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usr_id", nullable = false)
	@JsonBackReference
    private User usr;
//    @Transient
//    @ElementCollection(targetClass=String.class)
//    private List<String> productsBarcode = new ArrayList<>();
    @OneToMany(targetEntity = Product.class, mappedBy = "cart", fetch=FetchType.EAGER)
//    @Transient
    private List<Product> inProducts = new ArrayList<>();

//    @Transient
//    private final APIClient apiClient;
//
//    public Cart(APIClient apiClient) {
//        this.apiClient = apiClient;
//    }
//    public Cart(){};
//
//    @Transient
//    public long getTotalNutritionalScore(){
//        long sum = 0 ;
//        for (String barcode : this.productsBarcode){
//            this.inProducts.add(this.apiClient.fetchProduct(barcode).getProduct());
//        }
//        for (Product op : this.inProducts) {
//            sum += op.nutritionalScore();
//        }
//        return sum;
//    }

    @Transient
    public int getNumberOfProducts() {
        return this.inProducts.size();
    }
}
