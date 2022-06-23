package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.dto.RestaurantImageDto;
import com.Boda.Yamyam.service.RestaurantImageService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RestaurantImageController {

    private final RestaurantImageService restaurantImageService;

    @PostMapping("/user/restaurant/writefile")
    @JsonProperty("")
    public ResponseEntity<Void> write(@RequestParam String restaurantId , @RequestPart MultipartFile multipartFile ) throws Exception {
        restaurantImageService.write(restaurantId, multipartFile);

        return ResponseEntity.ok(null);
    }


    @GetMapping("/user/restaurant/{restaurantId}")
    public RestaurantImageDto read(@PathVariable("restaurantId") String restaurantId) {

        return restaurantImageService.read(restaurantId);
    }
}
