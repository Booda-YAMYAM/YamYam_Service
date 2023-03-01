package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.dto.RestaurantDto;
import com.Boda.Yamyam.repository.RestaurantRepository;
import com.Boda.Yamyam.service.Initialize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class Initialize_And_FilterController {


    private final RestaurantRepository restaurantRepository;

    private final Initialize initialize;


    @GetMapping("/restaurant")
    public List<RestaurantDto> initialList() {

        initialize.initRestaurant();
        initialize.initMenu();
        initialize.initRestaurantImage();
        initialize.initMenuImage();

        List<RestaurantDto> restaurantDtos = new ArrayList<>();

        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
        for (Restaurant restaurant : allRestaurant) {

            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setX_coordinate(restaurant.getX_coordinate());
            restaurantDto.setY_coordinate(restaurant.getY_coordinate());
            restaurantDto.setAddress(restaurant.getAddress());
            restaurantDto.setRestaurantName(restaurant.getRestaurantName());
            restaurantDto.setRestaurantNumber(restaurant.getRestaurantNumber());
            restaurantDto.setHeart(restaurant.getHeart());
            restaurantDto.setCategory(restaurant.getCategory());
            restaurantDto.setRestaurantId(restaurant.getRestaurantId());
            restaurantDtos.add(restaurantDto);
        }


        return restaurantDtos;
    }



    @GetMapping("/restaurant/{category}")
    public List<RestaurantDto> filterList(@PathVariable("category") String category) {

        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();

        List<RestaurantDto> filterRestaurant = new ArrayList<>();

        for (Restaurant restaurant : allRestaurant) {

            if (restaurant.getCategory().equals(category)) {

                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setX_coordinate(restaurant.getX_coordinate());
                restaurantDto.setY_coordinate(restaurant.getY_coordinate());
                restaurantDto.setHeart(restaurant.getHeart());
                restaurantDto.setCategory(restaurant.getCategory());
                restaurantDto.setRestaurantName(restaurant.getRestaurantName());
                restaurantDto.setRestaurantNumber(restaurant.getRestaurantNumber());
                restaurantDto.setAddress(restaurant.getAddress());
                restaurantDto.setRestaurantId(restaurant.getRestaurantId());

                filterRestaurant.add(restaurantDto);
            }


        }

        return filterRestaurant;
    }

}
