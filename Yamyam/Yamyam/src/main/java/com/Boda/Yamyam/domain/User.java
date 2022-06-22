package com.Boda.Yamyam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    private String userId;

    private String password;

    private  String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<Restaurant> restaurants = new ArrayList<>();


}
