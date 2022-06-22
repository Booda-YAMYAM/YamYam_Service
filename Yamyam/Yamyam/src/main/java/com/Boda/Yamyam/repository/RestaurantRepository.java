package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant){em.persist(restaurant);}

    public List<Restaurant> findAllRestaurant() {
        return em.createQuery("select r from Restaurant r", Restaurant.class)
                .getResultList();
    }
}
