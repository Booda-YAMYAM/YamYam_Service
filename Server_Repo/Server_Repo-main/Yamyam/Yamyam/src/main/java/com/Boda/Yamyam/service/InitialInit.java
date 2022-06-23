package com.Boda.Yamyam.service;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.MenuImage;
import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.RestaurantImage;
import com.Boda.Yamyam.repository.MenuImageRepository;
import com.Boda.Yamyam.repository.MenuRepository;
import com.Boda.Yamyam.repository.RestaurantImageRepository;
import com.Boda.Yamyam.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
@Transactional
@RequiredArgsConstructor
public class InitialInit {

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    private final RestaurantImageRepository restaurantImageRepository;

    private final MenuImageRepository menuImageRepository;

    String[] restaurantAddressList = {
            "서울 종로구 대학로8가길 80 2층 (우)03086",
            "서울 종로구 동숭길 113 정보빌딩 1층 (우)03086",
            "서울 종로구 대학로 146 3층 (우)03084",
            "서울 종로구 대명길 39-9 2층 (우)03078",
            "서울 종로구 대학로9길 35 1층",
            "서울 종로구 대학로 143 1층 (우)03077",
            "서울 종로구 동숭길 68",
            "서울 종로구 대학로10길 24 스타리움빌딩 1층",
            "서울 종로구 대학로12길 53 3층",
            "서울 종로구 대학로9길 23-2 2층",
            "서울 종로구 동숭3길 6-4 1층"
    };

    String[] restaurantNameList = {
            "더키친750", "타셴", "하이디라오 대학로점", "소친친", "호호식당 대학로점", "리춘시장 대학로CGV점", "코야코", "빌리엔젤 대학로점", "서울프랑스", "서양집", "혜화동버거"
    };

    String[] restaurantPhoneNumberList = {
            "02-743-0554", "02-3673-4115", "02-743-6868", "02-742-8594", "02-741-2384", "02-3673-0410", "02-763-8587", "070-5051-8136", "02-747-7725", "02-764-7725", "02-744-0125"
    };

    String[] restaurantCategoryList = {
            "west", "cafe", "china", "china", "japan", "china", "korea", "cafe", "west", "west", "west"
    };

    String[][] menuNameList = {
            {"리코타 피자", "리코타 크림", "칼조네 리코타", "토마토 칼조네"},
            {"타셴 클래식 버거", "타셴 시그니처", "버거 더블치즈 버거", "더블패티 버거"},
            {"마라탕"},
            {"라즈지츠", "몽골리안 폭찹 (소) ", "칠리새우 (소) ", "향라새우"},
            {"사케동", "히레가츠정식", "가츠나베정식", "우나기동", "돈가츠정식"},
            {"꿔바루", "멘보샤", "마파두부", "새우완탕"},
            {"치즈떡볶이", "해물떡볶이", "순대떡볶이", "부대떡볶이"},
            {"녹차 마카롱", "흑임자 마카롱", "블루베리크림치즈", "아메리카노"},
            {"뵈프 부르기뇽 ", "라따뚜이", "시금치 크림 파스타", "라구소스 파스타"},
            {"채끝등심 스테이크", "포모도로 파스타", "불고기 크림 파스타", "고르곤 블루 리조또"},
            {"혜화동 치즈 버거", "도우넛 치즈 버거", "크리스피 치즈 버거", "쉬림프 어니언 버거"},
    };


    Long[][] menuPriceList = {
            {4500L, 4500L, 4500L, 4500L},
            {5000L, 5000L, 4500L, 3500L},
            {5000L},
            {5500L, 5000L, 5000L, 3500L},
            {3500L, 4500L, 3500L, 3500L, 4500L},
            {3500L, 3500L, 3500L, 3500L},
            {3500L, 5000L, 4500L, 3500L},
            {3500L, 5500L, 3500L, 4500L},
            {5000L, 3500L, 5500L, 5000L},
            {3500L, 4500L, 3500L, 5500L},
            {5000L, 3500L, 4500L, 3500L},
    };

    public void initRestaurant() {

        for (int i = 0; i < restaurantNameList.length; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(restaurantNameList[i]);
            restaurant.setRestaurantNumber(restaurantPhoneNumberList[i]);
            restaurant.setAddress(restaurantAddressList[i]);
            restaurant.setHeart(0L);
            restaurant.setCategory(restaurantCategoryList[i]);
            restaurant.setX_coordinate(getXYAddress(restaurantAddressList[i]).getDouble("x"));
            restaurant.setY_coordinate(getXYAddress(restaurantAddressList[i]).getDouble("y"));
            restaurantRepository.save(restaurant);
        }
    }

    public void initMenu() {
        for (int i = 0; i < menuNameList.length; i++) {

            Restaurant findRestaurant = restaurantRepository.findByRestaurantId((long) i+1);

            for (int j = 0; j < menuNameList[i].length; j++) {
                System.out.println( menuNameList[i].length);;
                Menu menu = new Menu();
                menu.setMenuName(menuNameList[i][j]);
                menu.setPrice(menuPriceList[i][j]);
                menu.setCartegory(findRestaurant.getCategory());
                menu.setRestaurant(findRestaurant);

                findRestaurant.getMenus().add(menu);

                menuRepository.save(menu);
            }
        }
    }

    public void initRestaurantImage() {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\restaurant\\burgerS.jpg";

        Restaurant restaurant = restaurantRepository.findByRestaurantId(2L);

        RestaurantImage restaurantImage = new RestaurantImage();
        restaurantImage.setRestaurant(restaurant);
        restaurantImage.setFilePath(filePath);
        restaurantImage.setFileName("burgers.jpg");

        restaurantImageRepository.save(restaurantImage);
    }

    public void initMenuImage(){
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\menu\\burger1.jpg";

        Menu menu1 = menuRepository.findByMenuId(5L);
        MenuImage menuImage1 = new MenuImage();

        menuImage1.setMenu(menu1);
        menuImage1.setFilePath(filePath);
        menuImage1.setFileName("burger1.jpg");
        menuImageRepository.save(menuImage1);

        Menu menu2 = menuRepository.findByMenuId(6L);
        filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\menu\\burger2.jpg";
        MenuImage menuImage2 = new MenuImage();
        menuImage2.setMenu(menu2);
        menuImage2.setFilePath(filePath);
        menuImage2.setFileName("burger2.jpg");
        menuImageRepository.save(menuImage2);

        Menu menu3 = menuRepository.findByMenuId(7L);
        filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\menu\\burger3.jpg";
        MenuImage menuImage3 = new MenuImage();
        menuImage3.setMenu(menu3);
        menuImage3.setFilePath(filePath);
        menuImage3.setFileName("burger3.jpg");
        menuImageRepository.save(menuImage3);

        Menu menu4 = menuRepository.findByMenuId(8L);
        filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\menu\\burger4.jpg";
        MenuImage menuImage4 = new MenuImage();
        menuImage4.setMenu(menu4);
        menuImage4.setFilePath(filePath);
        menuImage4.setFileName("burger4.jpg");
        menuImageRepository.save(menuImage4);

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
