package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsImage;
import ru.skypro.homework.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdsMapper {

    public ResponseWrapperAds toResponseWrapperAds(List<AdsDto> adsDtoCollection) {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(adsDtoCollection.size());
        responseWrapperAds.setResults(adsDtoCollection);
        return responseWrapperAds;
    }

    public AdsDto toAdsDto(Ads ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor(ads.getUser().getId());
        adsDto.setPk(ads.getPk());
        adsDto.setTitle(ads.getTitle());
        adsDto.setPrice(ads.getPrice());
        return adsDto;
    }

    public Ads toAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.setTitle(adsDto.getTitle());
        ads.setPrice(adsDto.getPrice());
        ads.getUser().setId(adsDto.getAuthor());
        return ads;
    }

    public Ads toAds(CreateAds createAds) {
        Ads ads = new Ads();
        ads.setTitle(createAds.getTitle());
        ads.setPrice(createAds.getPrice());
        ads.setDescription(createAds.getDescription());
        return ads;
    }

}
