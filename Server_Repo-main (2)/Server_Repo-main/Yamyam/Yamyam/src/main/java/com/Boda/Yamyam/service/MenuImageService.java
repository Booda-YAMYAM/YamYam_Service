package com.Boda.Yamyam.service;

import com.Boda.Yamyam.domain.Menu;
import com.Boda.Yamyam.domain.MenuImage;
import com.Boda.Yamyam.dto.MenuImageDto;
import com.Boda.Yamyam.repository.MenuImageRepository;
import com.Boda.Yamyam.repository.MenuRepository;
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
public class MenuImageService {

    private final MenuImageRepository menuImageRepository;

    private MenuRepository menuRepository;

    public void write(String menuId, MultipartFile multipartFile) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\menu";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + multipartFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        multipartFile.transferTo(saveFile);


        Menu findMenu = menuRepository.findByMenuId(Long.parseLong(menuId));
        MenuImage menuImage = new MenuImage();
        menuImage.setMenu(findMenu);
        menuImage.setFileName(fileName);
        menuImage.setFilePath("/files/ " + fileName);

        menuImageRepository.save(menuImage);
    }

    public MenuImageDto read(String menuId){
        MenuImageDto imageDto = new MenuImageDto();
        Menu findMenu = menuRepository.findByMenuId(Long.parseLong(menuId));
        List<MenuImage> menuImages = findMenu.getMenuImages();

        imageDto.setMenuId(menuId);
        imageDto.setImagePath(menuImages.get(0).getFilePath());

        return imageDto;
    }
}
