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
    private String color;
    private String classe;
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

    private long calculateNutritionalScore(){
        return this.negatifScore() - this.positifScore();
    }

    public void determineQualities(){
        this.qualities = new ArrayList<>();
        if (nutriments.energyScore() <= 3 ) this.qualities.add("Densité énergétique (" + nutriments.energyScore() + ")") ;
        if (nutriments.saturatedFatScore() <= 3 )this.qualities.add("Graisses saturées (" + nutriments.saturatedFatScore() + ")") ;
        if (nutriments.saltScore() <= 3 ) this.qualities.add("Sodium1 (" + nutriments.saltScore() + ")") ;
        if (nutriments.sugarScore() <= 3 ) this.qualities.add("Sucres simples (" + nutriments.sugarScore() + ")") ;
        if (nutriments.fibersScore() >= 2 ) this.qualities.add("Fibres (" + nutriments.fibersScore() + ")") ;
        if (nutriments.proteinsScore() >= 2 ) this.qualities.add( "Proteins (" + nutriments.proteinsScore() + ")");
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
        if (nutriments.energyScore() >= 7 ) this.flaws.add("Densité énergétique (" + nutriments.energyScore() + ")") ;
        if (nutriments.saturatedFatScore() >= 7 ) this.flaws.add("Graisses saturées (" + nutriments.saturatedFatScore() + ")");
        if (nutriments.saltScore() >= 7 ) this.flaws.add("Sodium1 (" + nutriments.saltScore() + ")");
        if (nutriments.sugarScore() >= 7 ) this.flaws.add("Sucres simples (" + nutriments.sugarScore() + ")");
        if (nutriments.fibersScore() <= 0 ) this.flaws.add("Fibres (" + nutriments.fibersScore() + ")" );
        if (nutriments.proteinsScore() <= 0 ) this.flaws.add("Proteins (" + nutriments.proteinsScore() + ")");
    }

    public long getNutritional_score(){
        this.nutritional_score = this.calculateNutritionalScore();
        return this.nutritional_score;
    }

    public String getColor(){
        long score = getNutritional_score();
        if(score <= -1) {
            this.color = "Green";
        } else if(score <= 2) {
            this.color = "Light green";
        } else if(score <= 10) {
            this.color = "Yellow";
        } else if(score <= 18) {
            this.color = "Orange";
        } else {
            this.color = "Red";
        }
        return this.color;
    }

    public String getClasse(){
        long score = getNutritional_score();
        if(score <= -1) {
            this.classe = "Trop bon";
        } else if(score <= 2) {
            this.classe = "Bon";
        } else if(score <= 10) {
            this.classe = "Mangeable";
        } else if(score <= 18) {
            this.classe = "Mouai";
        } else {
            this.classe = "Degueu";
        }
        return this.classe;
    }

}
