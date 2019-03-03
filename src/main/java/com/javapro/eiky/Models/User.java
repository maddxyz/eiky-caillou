package com.javapro.eiky.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usr")
    private List<Cart> carts = new ArrayList<Cart>();

}
