package com.Boda.Yamyam.service;

import com.Boda.Yamyam.domain.Restaurant;
import com.Boda.Yamyam.domain.RestaurantImage;
import com.Boda.Yamyam.dto.RestaurantImageDto;
import com.Boda.Yamyam.repository.RestaurantImageRepository;
import com.Boda.Yamyam.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantImageService {

    private final RestaurantImageRepository restaurantImageRepository;

    private final RestaurantRepository restaurantRepository;

    public void write(String restaurantId, MultipartFile multipartFile) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\restaurant";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + multipartFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        multipartFile.transferTo(saveFile);

        Restaurant findRestaurant = restaurantRepository.findByRestaurantId(Long.parseLong(restaurantId));
        RestaurantImage restaurantImage = new RestaurantImage();
        restaurantImage.setRestaurant(findRestaurant);
        restaurantImage.setFileName(fileName);
        restaurantImage.setFilePath("/files/ " + fileName);

        restaurantImageRepository.save(restaurantImage);
    }

    public RestaurantImageDto read(String restaurantId){
        RestaurantImageDto restaurantImageDto = new RestaurantImageDto();
        Restaurant findMenu = restaurantRepository.findByRestaurantId(Long.parseLong(restaurantId));
        RestaurantImage restaurantImages = findMenu.getRestaurantImages();

        restaurantImageDto.setRestaurantId(restaurantId);
        restaurantImageDto.setImagePath(restaurantImages.getFilePath());

        return restaurantImageDto;
    }

}
