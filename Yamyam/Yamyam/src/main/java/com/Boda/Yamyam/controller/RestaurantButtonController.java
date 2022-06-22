package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantButtonController {

    private User user;

    @Transactional
    @GetMapping("/restaurant/{restaurantId}")
    public Object projectInfo(@PathVariable("restaurantId") Long restaurantId) {


        List<Restaurant> restaurants = user.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if(restaurantId==restaurant.getRestaurantId()){
                return restaurant;
            }
        }
        return null;
    }
}
