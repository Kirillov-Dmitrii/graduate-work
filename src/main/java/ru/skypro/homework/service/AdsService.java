package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.mappers.AdsMapper;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AdsService {

    private AdsRepository adsRepository;

    private AdsImageRepository adsImageRepository;

    public AdsService(AdsRepository adsRepository, AdsImageRepository adsImageRepository) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
    }

    public ResponseWrapperAds getAll() {
        List<Ads> allAds = adsRepository.findAll();
        ResponseWrapperAds responseWrapperAds;
        if (!allAds.isEmpty()) {
            Collection<AdsDto> adsDtoCollection = Collections.emptyList();
            allAds.forEach(ads -> {
                AdsDto adsDto = AdsMapper.toAdsDto(ads);
                adsDtoCollection.add(adsDto);
            });
            responseWrapperAds = AdsMapper.toResponseWrapperAds(adsDtoCollection);
        } else {
            responseWrapperAds = new ResponseWrapperAds();
        }
        return responseWrapperAds;
    }
}
