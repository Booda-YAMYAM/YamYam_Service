package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class HomeController {

    private RestaurantRepository repository;
    private Menu menu;

    @Transactional
    @GetMapping("/")
    public void projectInfo() {
        for(int i=0;i<10;i++){


            Restaurant restaurant=new Restaurant();
            restaurant.setRestaurantName("testname");
            restaurant.setRestaurantNumber("010-xxxx-xxxx");

            restaurant.setX_coordinate((long)12);
            restaurant.setY_coordinate((long)12);
            restaurant.setAddress("서울");
            restaurant.setHeart((long)22);
            restaurant.setCategory("한식");
            repository.save(restaurant);

        }
    }

}
