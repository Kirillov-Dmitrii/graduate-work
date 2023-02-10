package ru.skypro.homework.service;

import org.springframework.stereotype.Controller;
import ru.skypro.homework.repository.AdsImageRepository;
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

}
