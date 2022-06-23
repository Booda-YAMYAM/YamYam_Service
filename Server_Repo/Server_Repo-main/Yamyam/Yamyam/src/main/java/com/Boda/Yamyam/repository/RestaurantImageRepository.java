package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {

    RestaurantImage findByRestaurant(Restaurant restaurant);
}
