package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.MenuImage;
import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.RestaurantImage;
import com.Boda.Yamyam.dto.MenuImageDto;
import com.Boda.Yamyam.dto.RestaurantDto;
import com.Boda.Yamyam.repository.MenuImageRepository;
import com.Boda.Yamyam.repository.MenuRepository;
import com.Boda.Yamyam.repository.RestaurantImageRepository;
import com.Boda.Yamyam.repository.RestaurantRepository;
import com.Boda.Yamyam.service.InitialInit;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class CategoryFilterController {


    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final MenuImageRepository menuImageRepository;

    private final InitialInit initialInit;


    @GetMapping("/restaurant")
    public List<RestaurantDto> initialList() {

        initialInit.initRestaurant();
        initialInit.initMenu();
        initialInit.initRestaurantImage();
        initialInit.initMenuImage();

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
        List<Menu> menuList = new ArrayList<>();
        for (Restaurant restaurant : allRestaurant) {
            List<Menu> menus = restaurant.getMenus();
            for (Menu menu : menus) {
                if (category.equals(menu.getCartegory())) {
                    menuList.add(menu);
                }
            }

        }
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
