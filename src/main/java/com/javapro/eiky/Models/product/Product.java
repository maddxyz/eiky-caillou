package com.javapro.eiky.Models.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javapro.eiky.Models.cart.Cart;
import com.javapro.eiky.Models.others.Nutriments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String quantity;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "nutriments_id")
    private Nutriments nutriments;
    private String code;
    private String product_name;
    private long nutritional_score;
//    private String qualities;
//    private String flaws;
    @ElementCollection(targetClass=String.class)
    private List<String> qualities;
    @ElementCollection(targetClass=String.class)
    private List<String> flaws;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    @JsonBackReference
    private Cart cart;

    private long negatifScore(){
        return nutriments.energyScore() + nutriments.saturatedFatScore() + nutriments.saltScore() + nutriments.sugarScore();
    }

    private long positifScore(){
        return nutriments.fibersScore() + nutriments.proteinsScore();
    }

    public long nutritionalScore(){
        return this.negatifScore() - this.positifScore();
    }

    public void determineQualities(){
        this.qualities = new ArrayList<>();
        if (nutriments.energyScore() <= 3 ) this.qualities.add("Densité énergétique (" + nutriments.energyScore() + "),") ;
        if (nutriments.saturatedFatScore() <= 3 )this.qualities.add(" Graisses saturées (" + nutriments.saturatedFatScore() + "),") ;
        if (nutriments.saltScore() <= 3 ) this.qualities.add(" Sodium1 (" + nutriments.saltScore() + "),") ;
        if (nutriments.sugarScore() <= 3 ) this.qualities.add(" Sucres simples (" + nutriments.sugarScore() + "),") ;
        if (nutriments.fibersScore() >= 2 ) this.qualities.add(" Fibres (" + nutriments.fibersScore() + "),") ;
        if (nutriments.proteinsScore() >= 2 ) this.qualities.add( " Proteins (" + nutriments.proteinsScore() + "),");
    }

    public List<String>  getQualities(){
        this.determineQualities();
        return this.qualities;
    }


    public List<String> getFlaws(){
        this.determineFlaws();
        return this.flaws;
    }

    public void determineFlaws(){
        this.flaws = new ArrayList<>();
        if (nutriments.energyScore() >= 7 ) this.flaws.add("Densité énergétique (" + nutriments.energyScore() + "),") ;
        if (nutriments.saturatedFatScore() >= 7 ) this.flaws.add(" Graisses saturées (" + nutriments.saturatedFatScore() + "),");
        if (nutriments.saltScore() >= 7 ) this.flaws.add(" Sodium1 (" + nutriments.saltScore() + "),");
        if (nutriments.sugarScore() >= 7 ) this.flaws.add(" Sucres simples (" + nutriments.sugarScore() + "),");
        if (nutriments.fibersScore() <= 0 ) this.flaws.add(" Fibres (" + nutriments.fibersScore() + ")," );
        if (nutriments.proteinsScore() <= 0 ) this.flaws.add(" Proteins (" + nutriments.proteinsScore() + "),");
    }

    public void calculateNutritionalScore(){
        this.nutritional_score = this.nutritionalScore();
    }

}
