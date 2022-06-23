package com.Boda.Yamyam.dto;

import com.Boda.Yamyam.domain.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 프론트단에 보내기 위한 DTO
@Getter
@Setter
public class RestaurantDto {

    private Long restaurantId;

    private Double X_coordinate;

    private Double Y_coordinate;

    private String address;

    private String restaurantName;

    private String restaurantNumber;

    private Long heart;

    private String category;

    private String restaurantImages;
//    private List<Menu> menus;

}