package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.component.Authority;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;

@Controller
public class AdsImageService {

    private final AdsImageRepository adsImageRepository;

    private final AdsService adsService;

    public AdsImageService(AdsImageRepository adsImageRepository, AdsService adsService) {
        this.adsImageRepository = adsImageRepository;
        this.adsService = adsService;
    }

    public byte[] get(String id) {
        if (adsImageRepository.existsById(id)) {
            return adsImageRepository.findById(id).get().getData();
        } else {
            return null;
        }
    }
    @Transactional
    public byte[] update(Integer id, MultipartFile image, Authentication authentication) {
        byte[] data;
        if (Authority.check(authentication).equals(Role.ADMIN.toString()) ||
                (Authority.check(authentication).equals(Role.USER.toString())
                        && adsImageRepository.existsByAds_PkAndAds_User_Username(id, authentication.getName()))) {
            AdsImage adsImage = adsImageRepository.findByAds_Pk(id);
            adsImage.setFileSize(image.getSize());
            adsImage.setMediaType(image.getOriginalFilename().substring(image.getOriginalFilename().indexOf(".") + 1));
            try {
                data = image.getBytes();
                adsImage.setData(data);
                adsImageRepository.save(adsImage);
                return data;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
