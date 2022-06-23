package com.Boda.Yamyam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    private String menuName;

    private Long price;

    private String cartegory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private List<MenuImage> menuImages = new ArrayList<>();

}
