package com.Boda.Yamyam.repository;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.MenuImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuImageRepository extends JpaRepository<MenuImage, Long> {

    MenuImage findByMenu(Menu menu);
}
