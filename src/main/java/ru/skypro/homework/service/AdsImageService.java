package ru.skypro.homework.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;

import java.io.IOException;

@Controller
public class AdsImageService {

    private final AdsImageRepository adsImageRepository;

    public AdsImageService(AdsImageRepository adsImageRepository) {
        this.adsImageRepository = adsImageRepository;
    }

    public byte[] get(String id) {
        if (adsImageRepository.existsById(id)) {
            return adsImageRepository.findById(id).get().getData();
        }
        return new byte[0];
    }

    public byte[] update(String id, MultipartFile image) {
        if (adsImageRepository.existsById(id)) {
            AdsImage adsImage = adsImageRepository.findById(id).get();
            adsImage.setFileSize(image.getSize());
            adsImage.setMediaType(image.getOriginalFilename().substring(image.getOriginalFilename().indexOf(".") + 1));
            try {
                adsImage.setData(image.getBytes());
                adsImageRepository.save(adsImage);
                return image.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new byte[0];
    }

}
