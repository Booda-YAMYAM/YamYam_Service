package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    @Transactional
    public void save(Restaurant restaurant){em.persist(restaurant);}

    public List<Restaurant> findAllRestaurant() {
        System.out.println(em.createQuery("select r from Restaurant r", Restaurant.class));
        return em.createQuery("select r from Restaurant r", Restaurant.class)
                .getResultList();

    }
    public Restaurant findByRestaurantId(Long restaurantId){
        return em.createQuery("select m from Restaurant m where m.restaurantId = :restaurantId", Restaurant.class)
                .setParameter("restaurantId",restaurantId )
                .getSingleResult();
    }
}
