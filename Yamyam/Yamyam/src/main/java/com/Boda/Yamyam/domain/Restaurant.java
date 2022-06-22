package com.Boda.Yamyam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    private String restaurantName;

    private  String restaurantNumber;

    private Double X_coordinate;

    private Double Y_coordinate;

    private  String address;

    private Long heart;

    private  String category;


    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
    private List<Menu> menus = new ArrayList<>();




}



