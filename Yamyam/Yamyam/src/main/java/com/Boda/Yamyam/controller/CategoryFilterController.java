package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryFilterController {


    private final RestaurantRepository restaurantRepository;
    private double latitude;
    private double longitude;
//    HashMap<String,Double> XYlocation=new HashMap<>();

    @GetMapping("/restaurant")
    public List<Restaurant> initialList() {


        for (int i = 0; i < 10; i++) {


            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurantName("이차돌");
            restaurant.setRestaurantNumber("010-xxxx-xxxx");
            restaurant.setAddress("판교역로 235");
            restaurant.setX_coordinate(getAddress(restaurant.getAddress()).getDouble("x"));
            restaurant.setY_coordinate(getAddress(restaurant.getAddress()).getDouble("y"));


            restaurant.setHeart((long) 22);
            restaurant.setCategory("korea");
            restaurantRepository.save(restaurant);

        }

        for (int i = 0; i < 10; i++) {


            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurantName("홍콩반점");
            restaurant.setRestaurantNumber("010-xxxx-xxxx");
            restaurant.setAddress("경기도 성남시 분당구 대왕판교로385번길 28");
            restaurant.setX_coordinate(getAddress(restaurant.getAddress()).getDouble("x"));
            restaurant.setY_coordinate(getAddress(restaurant.getAddress()).getDouble("y"));


            restaurant.setHeart((long) 222);
            restaurant.setCategory("china");
            restaurantRepository.save(restaurant);

        }
        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
        return allRestaurant;
    }

    @GetMapping("/restaurant/{category}")
    public List<Restaurant> filterList(@PathVariable("category") String category) {

        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
        List<Restaurant> filterRestaurant = new ArrayList<>();
        for (Restaurant restaurant : allRestaurant) {

            if (restaurant.getCategory().equals(category)) {
                filterRestaurant.add(restaurant);
            }


        }


        return filterRestaurant;
    }

    @GetMapping("/{address}")
    public    Double  address(@PathVariable("address") String address) {
        return getAddress(address).getDouble("y");


    }




    public JSONObject getAddress(String roadFullAddr) {

        try {

            String location = roadFullAddr;

            String addr = "https://dapi.kakao.com/v2/local/search/address.json";

            String apiKey = "KakaoAK c983d2c2b94cdfa10aafcae29ec7eb86";

            location = URLEncoder.encode(location, "UTF-8");

            String query = "query=" + location;

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(addr);
            stringBuffer.append("?");
            stringBuffer.append(query);

            System.out.println("stringBuffer.toString() " + stringBuffer.toString());

            URL url = new URL(stringBuffer.toString());

            URLConnection conn = url.openConnection();

            conn.setRequestProperty("Authorization", apiKey);

            BufferedReader rd = null;

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();

            String line;

            while ((line = rd.readLine()) != null) {
                docJson.append(line);
            }

            if (0 < docJson.toString().length()) {
                System.out.println("docJson    :" + docJson.toString());

            }

            rd.close();

            JSONObject jsonObject = new JSONObject(docJson.toString());

            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");

            JSONObject tempObj = (JSONObject) jsonArray.get(0);

            System.out.println("latitude : " + tempObj.getDouble("y"));
            System.out.println("longitude : " + tempObj.getDouble("x"));


            return tempObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
