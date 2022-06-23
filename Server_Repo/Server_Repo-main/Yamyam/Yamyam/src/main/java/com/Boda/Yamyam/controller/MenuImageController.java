package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.dto.MenuImageDto;
import com.Boda.Yamyam.service.MenuImageService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MenuImageController {

    private final MenuImageService menuImageService;


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
}
