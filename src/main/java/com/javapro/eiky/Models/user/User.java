package com.javapro.eiky.Models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javapro.eiky.Models.cart.Cart;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @JsonIgnore
    @OneToMany(mappedBy = "usr", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<Cart>();

}
