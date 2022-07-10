package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Override
    List<Menu> findAll();

    Menu findByMenuId(Long menuId);
}
