package com.javapro.eiky.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Nutriments {
    @Id
    @GeneratedValue
    private Long id;
    private float energy_100g;
    @JsonProperty("saturated-fat_100g")
    private float saturated_fat_100g;
    private float sugars_100g;
    private float salt_100g;
    private float fiber_100g;
    private float proteins_100g;


    // Getter Methods

    public Long getId() {
        return id != null ? id : 0;
    }

    public float getSalt_100g() {
        return salt_100g;
    }

    public float getSugars_100g() {
        return sugars_100g;
    }

    public float getFiber_100g() {
        return fiber_100g;
    }

    public float getEnergy_100g() {
        return energy_100g;
    }

    public float getProteins_100g() {
        return proteins_100g;
    }

    public float getSaturated_fat_100g() {
        return saturated_fat_100g;
    }

    // Setter Methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalt_100g(float salt_100g) {
        this.salt_100g = salt_100g;
    }

    public void setSugars_100g(float sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

    public void setFiber_100g(float fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public void setEnergy_100g(float energy_100g) {
        this.energy_100g = energy_100g;
    }

    public void setProteins_100g(float proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public void setSaturated_fat_100g(float saturated_fat_100g) {
        this.saturated_fat_100g = saturated_fat_100g;
    }

    public int energyScore(){
        int score = 0;
        if (this.energy_100g > 335 ) score = 1 ;
        if (this.energy_100g > 670 ) score = 2 ;
        if (this.energy_100g > 1005) score = 3 ;
        if (this.energy_100g > 1340) score = 4 ;
        if (this.energy_100g > 1675) score = 5 ;
        if (this.energy_100g > 2010) score = 6 ;
        if (this.energy_100g > 2345) score = 7 ;
        if (this.energy_100g > 2680) score = 8 ;
        if (this.energy_100g > 3015) score = 9 ;
        if (this.energy_100g > 3350) score = 10;
        return score;
    }

    public int saturatedFatScore(){
        int score = 0;
        if (this.saturated_fat_100g > 1 ) score = 1 ;
        if (this.saturated_fat_100g > 2 ) score = 2 ;
        if (this.saturated_fat_100g > 3 ) score = 3 ;
        if (this.saturated_fat_100g > 4 ) score = 4 ;
        if (this.saturated_fat_100g > 5 ) score = 5 ;
        if (this.saturated_fat_100g > 6 ) score = 6 ;
        if (this.saturated_fat_100g > 7 ) score = 7 ;
        if (this.saturated_fat_100g > 8 ) score = 8 ;
        if (this.saturated_fat_100g > 9 ) score = 9 ;
        if (this.saturated_fat_100g > 10) score = 10;
        return score;
    }

    public int sugarScore(){
        int score = 0;
        if (this.sugars_100g > 4.5 ) score = 1 ;
        if (this.sugars_100g > 9   ) score = 2 ;
        if (this.sugars_100g > 13.5) score = 3 ;
        if (this.sugars_100g > 18  ) score = 4 ;
        if (this.sugars_100g > 22.5) score = 5 ;
        if (this.sugars_100g > 27  ) score = 6 ;
        if (this.sugars_100g > 31  ) score = 7 ;
        if (this.sugars_100g > 36  ) score = 8 ;
        if (this.sugars_100g > 40  ) score = 9 ;
        if (this.sugars_100g > 45  ) score = 10;
        return score;
    }

    public int saltScore(){
        int score = 0;
        if (this.salt_100g > 90 ) score = 1 ;
        if (this.salt_100g > 180) score = 2 ;
        if (this.salt_100g > 270) score = 3 ;
        if (this.salt_100g > 360) score = 4 ;
        if (this.salt_100g > 450) score = 5 ;
        if (this.salt_100g > 540) score = 6 ;
        if (this.salt_100g > 630) score = 7 ;
        if (this.salt_100g > 720) score = 8 ;
        if (this.salt_100g > 810) score = 9 ;
        if (this.salt_100g > 900) score = 10;
        return score;
    }

    public int fibersScore(){
        int score = 0;
        if (this.fiber_100g > 0.9) score = 1 ;
        if (this.fiber_100g > 1.9) score = 2 ;
        if (this.fiber_100g > 2.8) score = 3 ;
        if (this.fiber_100g > 3.7) score = 4 ;
        if (this.fiber_100g > 4.7) score = 5 ;
        return score;
    }

    public int proteinsScore(){
        int score = 0;
        if (this.proteins_100g > 1.6) score = 1 ;
        if (this.proteins_100g > 3.2) score = 2 ;
        if (this.proteins_100g > 4.8) score = 3 ;
        if (this.proteins_100g > 6.4) score = 4 ;
        if (this.proteins_100g > 8.0) score = 5 ;
        return score;
    }
}
