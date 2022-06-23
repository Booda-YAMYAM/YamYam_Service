package com.Boda.Yamyam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RestaurantImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RestaurantImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String fileName;

    private String filePath;

}
