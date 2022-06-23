package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final EntityManager em;

    @Transactional
    public void save(Menu menu){em.persist(menu);}

    public List<Menu> findAllMenu() {
        return em.createQuery("select m from Menu m", Menu.class)
                .getResultList();
    }

    public Menu findByMenuId(Long menuId){
        return em.createQuery("select m from Menu m where m.menuId = :menuId", Menu.class)
                .setParameter("menuId",menuId )
                .getSingleResult();
    }
}
