package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.dto.RestaurantDto;
import com.Boda.Yamyam.repository.MenuRepository;
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
    private final MenuRepository menuRepository;
    List<Menu> menulist = new ArrayList<>();


    @GetMapping("/restaurant")
    public List<Restaurant> initialList() {
//
//        ArrayList<String> shopname = new ArrayList<>();
//        ArrayList<String> Tel = new ArrayList<>();
//        ArrayList<String> location = new ArrayList<>();

        String[]  shopname= {"본가광양불고기",
                "메밀골동해막국수",
                "카페오빵",
                "화수분",
                "수리울",
                "들꽃가마솥곰탕",
                "고기동황금쉼터",
                "본죽&비빔밥 인천신포동점",
                "김밥천국",
                "유성식당",
                "연안반점",
                "고기리쉼터",
                "도깨비웍",
                "Chai797 ",
                "하이보",
                "짬뽕지존",
                "호접몽",
                "칭마오",
                "카페오빵",
                "놀이터",
                "CU 신포시장길점",
                "베스트홈푸드",
                "유성분식",
                "본죽&비빔밥 인천신포동점",
                "김밥천국",
                "유성식당",
                "연안반점",
                "CU 연안부두점",
                "베지박스",
                "갤러리카페다미안",
                "오데뜨",
                "모아니",
                "식구",
                "세븐일레븐",
                "김밥천국",
                "세븐일레븐",
                "이마트24",
                "CU",
                "GS25",
                "세븐일레븐",
                "후라토식당",
                "카츄마마",
                "스시오마카세",
                "고쿠텐",
                "미소야",
                "스시야",
                "호니도니",
                "호호스시",

        };

        String[] tel = {"041-853-6677",
                "041-857-8465",
                "041-853-7663",
                "041-857-3498",
                "041-857-5035",
                "041-857-4930",
                "041-855-7110",
                "041-881-4545",
                "02-0000-0000",
                "041-854-2787",
                "042-856-5701",
                "041-857-9901",
                "042-825-1559",
                "042-825-1505",
                "042-826-7818",
                "042-825-9388",
                "041-857-7006",
                "041-841-7766",
                "041-841-0624",
                "041-841-7356",
                "041-841-8075",
                "041-841-0624",
                "041-855-5667",
                "041-858-0454",
                "041-853-4900",
                "041-858-6602",
                "041-960-5769",
                "041-858-3901",
                "041-881-0711",
                "041-852-6462",
                "041-850-8045",
                "041-854-4004",
                "041-881-9220",
                "041-854-0405",
                "041-858-2452",
                "041-854-5968",
                "041-858-0574",
                "041-853-3901",
                "041-854-4000",
                "041-881-6271",
                "041-855-7395",
                "041-881-4170",
                "041-854-0025",
                "041-854-2888",
                "041-853-7150",
                "041-855-1211",
                "041-856-4523",
                "041-881-9511"
        };

        String[] location ={

                "판교역로 235 ",
                "인천 중구 신포동 2-1",
                "인천광역시 중구 신포로 18-1",
                "인천 중구 중산동 1885-1",
                "인천 중구 중산동 1885-1",
                "인천 중구 신포동 2-1",
                "인천광역시 중구 항동 758-54 　",
                "인천광역시 중구 항동7 27-131",
                "인천 중구 신흥동3가 31-49",
                "인천광역시 중구 신흥동 37-308",
                "인천 중구 제물량로 87 ",
                "인천시 중구 서해대로 458",
                "인천 중구 서해대로 464번길 7",
                "인천 중구 신흥동3가",
                "인천 중구 참외전로 244-1",
                "인천 중구 율목동 참외전로 190",
                "인천 중구 우현로 49-1",
                "인천광역시 중구 우현로83번길 1",
                "인천광역시 중구 참외전로 72번길20",
                "인천광역시 중구 자유공원로 3-12",
                "인천광역시 중구 우현로 93-1",
                "인천 중구 중산동 1885-1",
                "인천광역시 중구 인현동 22-40",
                "인천광역시 중구 제물량로 270-1",
                "인천 중구 북성동1가 98-559",
                "인천 중구 북성동1가 98-275",
                "인천광역시 중구 참외전로 53 ",
                "인천시 중구 운남로 150",
                "인천 중구 하늘별빛로 65번길 7-3",
                "인천 중구 영종대로 94",
                "인천광역시 중구 영종대로 94",
                "인천광역시 중구 영종대로 120",
                "인천 중구 흰바위로 31",
                "인천 중구 넙디로 13 ",
                "인천광역시 중구 영종대로327번길 4",
                "인천 중구 공항로 271",
                "인천 중구 신도시남로 141번길 6 ",
                "인천 중구 중산동 1885-1",
                "인천 중구 중산동 1885-1 ",
                "인천 중구 흰바위로 41",
                "인천광역시 중구 영종대로 84",
                "인천 중구 을왕동 755-16",
                "인천 중구 을왕동 773 ",
                "인천 중구 무의동 344-8",
                "인천 중구 중산동 1885-1",
                "인천광역시 중구 하늘중앙로195번길",
                "인천광역시 중구 하늘중앙로195번길",
                "인천 중구 하늘별빛로65번길 "
        };

        System.out.println("location.length = " + location.length);
        System.out.println("tel = " + tel.length);
        System.out.println("shopname = " + shopname.length);


        for (int i = 0; i < 47; i++) {
            Restaurant restaurant = new Restaurant();




            restaurant.setRestaurantName(shopname[i]);
            restaurant.setRestaurantNumber(tel[i]);
            restaurant.setAddress("인천광역시 중구 영종대로 84");
            restaurant.setX_coordinate(getXYAddress(restaurant.getAddress()).getDouble("x")+(double)i*0.23424 );
            restaurant.setY_coordinate(getXYAddress(restaurant.getAddress()).getDouble("y")-(double)i*0.25734);
            restaurant.setRestaurantId(1L);



            restaurant.setHeart((long) i);
            if(i<10)
                restaurant.setCategory("korea");
            else if (i<16)
                restaurant.setCategory("china");
            else if (i<24)
                restaurant.setCategory("france");
            else if (i<30)
                restaurant.setCategory("dessert");
            else if (i<35)
                restaurant.setCategory("cafe");
            else if (i<40)
                restaurant.setCategory("conviniencestore");
            else if (i<47)
                restaurant.setCategory("japan");






                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("비빔밥");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("korea");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("국수");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("korea");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);

                    }
                }



                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("짜장");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("china");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("짬뽕");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("china");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);

                    }
                }



                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("파스타");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("france");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("빠네");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("france");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);

                    }
                }



                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("케이크");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("dessert");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("팥빵");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("dessert");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);
                    }
                }



                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("아메리카노");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("cafe");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("라떼");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("cafe");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);

                    }
                }



                for (int j = 0; j < 1; j++) {
                    if (i==j){
                        Menu menu = new Menu();
                        menu.setPrice(10000L);
                        menu.setMenuName("라면");
                        menu.setMenuNum((long)i);
                        menu.setCartegory("conviniencestore");
                        menulist.add(menu);
                        menu.setRestaurant(restaurant);
                        menuRepository.save(menu);
                        Menu menu2 = new Menu();
                        menu2.setPrice(10000L);
                        menu2.setMenuName("김밥");
                        menu2.setMenuNum((long)i);
                        menu2.setCartegory("conviniencestore");
                        menulist.add(menu2);
                        menu2.setRestaurant(restaurant);
                        menuRepository.save(menu2);

                    }
                }



            for (int j = 0; j < 1; j++) {
                if (i==j){
                    Menu menu = new Menu();
                    menu.setPrice(10000L);
                    menu.setMenuName("초밥");
                    menu.setMenuNum((long)i);
                    menu.setCartegory("japan");
                    menulist.add(menu);
                    menu.setRestaurant(restaurant);
                    menuRepository.save(menu);
                    Menu menu2 = new Menu();
                    menu2.setPrice(10000L);
                    menu2.setMenuName("회");
                    menu2.setMenuNum((long)i);
                    menu2.setCartegory("japan");
                    menulist.add(menu2);
                    menu2.setRestaurant(restaurant);
                    menuRepository.save(menu2);

                }
            }


//            List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
//            List<Restaurant> initialRestaurant = new ArrayList<>();
//
//            List<Menu> initialmenuList=new ArrayList<>();
//            for (Restaurant restaurant : allRestaurant) {
//                List<Menu> menus = restaurant.getMenus();
//                for (Menu menu : menus) {
//                    if (restaurant.getCategory().equals(menu.getCartegory())){
//                        initialRestaurant.add(restaurant);
//                    }
//                }
//
//            }


            List<Menu> allMenu = menuRepository.findAllMenu();
            List<Menu> filterdMenu = new ArrayList<>();
            for (Menu menu : allMenu) {
                if(restaurant.getCategory().equals(menu.getCartegory())){
                    filterdMenu.add(menu);
                }
            }
            restaurant.setMenus(filterdMenu);

            restaurantRepository.save(restaurant);
        }
//
//        for (int i = 0; i < 5; i++) {
//            Restaurant restaurant = new Restaurant();
//
//
//
//
//            restaurant.setRestaurantName("이차돌");
//            restaurant.setRestaurantNumber("010-xxxx-xxxx");
//            restaurant.setAddress("서창남순환로110");
//            restaurant.setX_coordinate(getAddress(restaurant.getAddress()).getDouble("x"));
//            restaurant.setY_coordinate(getAddress(restaurant.getAddress()).getDouble("y"));
//
//
//            restaurant.setHeart((long) 22);
//            restaurant.setCategory("china");
//
//            for (int j = 0; j < 5; j++) {
//                if (i==j){
//                    Menu menu = new Menu();
//
//                    menu.setPrice(10000L);
//                    menu.setMenuName("고기");
//                    menu.setMenuNum((long)i);
//                    menulist.add(menu);
//                    menuRepository.save(menu);
//                }
//
//
//            }
//
//
//
//
//
//            restaurant.setMenus(menulist);
//            restaurantRepository.save(restaurant);
//        }
//
//






        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
        return allRestaurant;


//        Restaurant restaurant2 = new Restaurant();
//        Menu menu2 = new Menu();
//        menu2.setPrice(10000L);
//        menu2.setMenuName("탕수육 ");
//        menuRepository.save(menu2);
//        menulist.add(menu2);
//        menu2.setRestaurant(restaurant2);
//        restaurant.getMenus().add(menu2);
//        restaurant2.setMenus(menulist);
//
//
//        restaurant2.setRestaurantName("홍콩반점");
//        restaurant2.setRestaurantNumber("010-xxxx-xxxx");
//        restaurant2.setAddress("경기도 성남시 분당구 대왕판교로385번길 28");
//        restaurant2.setX_coordinate(getAddress(restaurant2.getAddress()).getDouble("x"));
//        restaurant2.setY_coordinate(getAddress(restaurant2.getAddress()).getDouble("y"));
//
//
//        restaurant2.setHeart((long) 222);
//        restaurant2.setCategory("china");
//        restaurantRepository.save(restaurant2);
//
//        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
//
//        List<RestaurantDto> allRestaurantDtos = new ArrayList<>();
//        for (Restaurant restaurant : allRestaurant) {
//
//            RestaurantDto restaurantDto = new RestaurantDto();
//            restaurantDto.setX_coordinate(restaurant.getX_coordinate());
//            restaurantDto.setY_coordinate(restaurant.getY_coordinate());
//            restaurantDto.setHeart(restaurant.getHeart());
//            restaurantDto.setCategory(restaurant.getCategory());
//            restaurantDto.setRestaurantName(restaurant.getRestaurantName());
//            restaurantDto.setRestaurantNumber(restaurant.getRestaurantNumber());
//            restaurantDto.setAddress(restaurant.getAddress());
//
//            allRestaurantDtos.add(restaurantDto);
//        }
//
//        return allRestaurantDtos;
    }


//    @GetMapping("/restaurant")
//    public List<RestaurantDto> restaurantList() {
//
//        //더미데이터
//        Restaurant restaurantt = new Restaurant();
//        restaurantt.setRestaurantName("이차돌");
//        restaurantt.setRestaurantNumber("010-xxxx-xxxx");
//        restaurantt.setAddress("판교역로 235");
//        restaurantt.setX_coordinate(getAddress(restaurantt.getAddress()).getDouble("x"));
//        restaurantt.setY_coordinate(getAddress(restaurantt.getAddress()).getDouble("y"));
//        restaurantt.setHeart((long) 22);
//        restaurantt.setCategory("korea");
//        restaurantRepository.save(restaurantt);
//
//        Restaurant restaurantt2 = new Restaurant();
//        restaurantt2.setRestaurantName("이차돌");
//        restaurantt2.setRestaurantNumber("010-xxxx-xxxx");
//        restaurantt2.setAddress("판교역로 235");
//        restaurantt2.setX_coordinate(getAddress(restaurantt2.getAddress()).getDouble("x"));
//        restaurantt2.setY_coordinate(getAddress(restaurantt2.getAddress()).getDouble("y"));
//        restaurantt2.setHeart((long) 22);
//        restaurantt2.setCategory("china");
//        restaurantRepository.save(restaurantt2);
//        //더미 데이터
//
//
//        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
//        List<RestaurantDto> allRestaurantDtos = new ArrayList<>();
//
//        for (Restaurant restaurant : allRestaurant) {
//
//            RestaurantDto restaurantDto = new RestaurantDto();
//            restaurantDto.setX_coordinate(restaurant.getX_coordinate());
//            restaurantDto.setY_coordinate(restaurant.getY_coordinate());
//            restaurantDto.setHeart(restaurant.getHeart());
//            restaurantDto.setCategory(restaurant.getCategory());
//            restaurantDto.setRestaurantName(restaurant.getRestaurantName());
//            restaurantDto.setRestaurantNumber(restaurant.getRestaurantNumber());
//            restaurantDto.setAddress(restaurant.getAddress());
//
//
//            allRestaurantDtos.add(restaurantDto);
//        }
//
//        return allRestaurantDtos;
//    }

    @GetMapping("/restaurant/{category}")
    public List<RestaurantDto> filterList(@PathVariable("category") String category) {

        List<Restaurant> allRestaurant = restaurantRepository.findAllRestaurant();
        List<Menu> menuList=new ArrayList<>();
        for (Restaurant restaurant : allRestaurant) {
            List<Menu> menus = restaurant.getMenus();
            for (Menu menu : menus) {
                if (category.equals(menu.getCartegory())){
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
                restaurantDto.setMenus(menuList);

                filterRestaurant.add(restaurantDto);
            }


        }

        return filterRestaurant;
    }





    public JSONObject getXYAddress(String roadFullAddr) {

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
