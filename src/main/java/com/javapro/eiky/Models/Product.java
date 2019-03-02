package com.javapro.eiky.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Product {
    @Id
    private String id;
    private String nutrition_grade_fr;
    private String stores;
    private String quantity;
    @OneToOne
    @JoinColumn(name = "nutriments_id")
    private Nutriments nutriments;
    private String code;
    private String product_name;
    private String no_nutrition_data;
    private String nutrition_data;


    // Getter Methods


    public String getNutrition_grade_fr() {
        return nutrition_grade_fr;
    }

    public String getStores() {
        return stores;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getId() {
        return id;
    }


    public Nutriments getNutriments() {
        return nutriments;
    }

    public String getCode() {
        return code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getNo_nutrition_data() {
        return no_nutrition_data;
    }


    public String getNutrition_data() {
        return nutrition_data;
    }


    public void setNutrition_grade_fr(String nutrition_grade_fr) {
        this.nutrition_grade_fr = nutrition_grade_fr;
    }


    public void setStores(String stores) {
        this.stores = stores;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setNo_nutrition_data(String no_nutrition_data) {
        this.no_nutrition_data = no_nutrition_data;
    }

    public void setNnutrition_data(String nutrition_data) {
        this.nutrition_data = nutrition_data;
    }

    private long negatifScore(){
        return nutriments.energyScore() + nutriments.saturatedFatScore() + nutriments.saltScore() + nutriments.sugarScore();
    }

    private long positifScore(){
        return nutriments.fibersScore() + nutriments.proteinsScore();
    }

    public long nutritionalScore(){
        return this.negatifScore() - this.positifScore();
    }

    private String getQualities(){
        String s = "Les qualités de " + this.product_name + " sont : ";
        if (nutriments.energyScore() <= 3 ) s = s + "Densité énergétique (" + nutriments.energyScore() + ")," ;
        if (nutriments.saturatedFatScore() <= 3 ) s = s + " Graisses saturées (" + nutriments.saturatedFatScore() + ")," ;
        if (nutriments.saltScore() <= 3 ) s = s + " Sodium1 (" + nutriments.saltScore() + ")," ;
        if (nutriments.sugarScore() <= 3 ) s = s + " Sucres simples (" + nutriments.sugarScore() + ")," ;
        if (nutriments.fibersScore() >= 2 ) s = s + " Fibres (" + nutriments.fibersScore() + ")," ;
        if (nutriments.proteinsScore() >= 2 ) s = s + " Proteins (" + nutriments.proteinsScore() + "),";
        return s;
    }

    private String getFlaws(){
        String s = "Les défauts de " + this.product_name + " sont : ";
        if (nutriments.energyScore() >= 7 ) s = s + "Densité énergétique (" + nutriments.energyScore() + ")," ;
        if (nutriments.saturatedFatScore() >= 7 ) s = s + " Graisses saturées (" + nutriments.saturatedFatScore() + "),";
        if (nutriments.saltScore() >= 7 ) s = s + " Sodium1 (" + nutriments.saltScore() + ")," ;
        if (nutriments.sugarScore() >= 7 ) s = s + " Sucres simples (" + nutriments.sugarScore() + ")," ;
        if (nutriments.fibersScore() <= 0 ) s = s + " Fibres (" + nutriments.fibersScore() + ")," ;
        if (nutriments.proteinsScore() <= 0 ) s = s + " Proteins (" + nutriments.proteinsScore() + "),";
        return s;
    }

    public String Synthesis(){
        String ret = "";
        String qstart = "Les qualités de" + this.product_name + " sont : ";
        String fstart = "Les défauts de" + this.product_name + " sont : ";
        if (getQualities().length() != qstart.length()){
            ret = ret + "<br>" + getQualities();
        }
        if (getFlaws().length() != fstart.length()){
            ret = ret + "<br>" + getFlaws();
        }
        return ret;
    }

}
