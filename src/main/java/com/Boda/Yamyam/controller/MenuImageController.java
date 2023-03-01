package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.MenuImage;
import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.RestaurantImage;
import com.Boda.Yamyam.dto.MenuImageDto;
import com.Boda.Yamyam.dto.RestaurantDto;
import com.Boda.Yamyam.repository.MenuImageRepository;
import com.Boda.Yamyam.repository.RestaurantImageRepository;
import com.Boda.Yamyam.repository.RestaurantRepository;
import com.Boda.Yamyam.service.MenuImageService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuImageController {

    private final MenuImageService menuImageService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final MenuImageRepository menuImageRepository;


    @PostMapping("/user/menu/writefile")
    @JsonProperty("")
    public ResponseEntity<Void> write(@RequestParam String menuId , @RequestPart MultipartFile multipartFile ) throws Exception {
        menuImageService.write(menuId, multipartFile);

        return ResponseEntity.ok(null);
    }


    @GetMapping("/user/menu/{menuId}")
    public MenuImageDto read(@PathVariable("menuId") String menuId) {

        return menuImageService.read(menuId);
    }

    //이미지 뿌리기
    @PostMapping("/restaurant")
    public RestaurantDto sendRestaurantImage(@RequestBody RestaurantDto restaurantDto) {

        Restaurant findRestaurant = restaurantRepository.findByRestaurantId(restaurantDto.getRestaurantId());
        RestaurantImage findRestaurantImage = restaurantImageRepository.findByRestaurant(findRestaurant);

        restaurantDto.setRestaurantImages(findRestaurantImage.getFilePath());


        return restaurantDto;
    }

    List<MenuImageDto> menuImageDtos = new ArrayList<>();
    @PostMapping("/restaurant/menu")
    @Transactional
    public List<MenuImageDto> sendMenuImage(@RequestBody RestaurantDto restaurantDto) {

        Restaurant findRestaurant = restaurantRepository.findByRestaurantId(restaurantDto.getRestaurantId());
        System.out.println("findRestaurant = " + findRestaurant);

        List<Menu> menus = findRestaurant.getMenus();

        for (Menu menu : menus) {

            MenuImage findMenuImage = menuImageRepository.findByMenu(menu);

            MenuImageDto menuImageDto = new MenuImageDto();
            menuImageDto.setMenuId(String.valueOf(findMenuImage.getMenu().getMenuId()));
            menuImageDto.setImagePath(findMenuImage.getFilePath());

            menuImageDtos.add(menuImageDto);
        }

        return menuImageDtos;
    }
}
