package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsImage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdsMapper {

    public static ResponseWrapperAds toResponseWrapperAds(Collection<AdsDto> adsDtoCollection) {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(adsDtoCollection.size());
        responseWrapperAds.setResults(adsDtoCollection);
        return responseWrapperAds;
    }

    public static AdsDto toAdsDto(Ads ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor(ads.getUser().getId());
        adsDto.setPk(ads.getPk());
        adsDto.setTitle(ads.getTitle());
        adsDto.setPrice(ads.getPrice());
        adsDto.setImage(ads.getAdsImage().stream().map(e -> e.getFilePath()).collect(Collectors.toList()));
        return adsDto;
    }

    public static Ads toAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.setTitle(adsDto.getTitle());
        ads.setPk(adsDto.getPk());
        ads.setPrice(adsDto.getPrice());
        ads.getUser().setId(adsDto.getAuthor());
        List<AdsImage> adsImage = Collections.emptyList();
        adsDto.getImage().forEach(image -> {
            AdsImage adsImage1 = new AdsImage();
            adsImage1.setFilePath(image);
            adsImage.add(adsImage1);
        });
        ads.setAdsImage(adsImage);
        return ads;
    }

    public static Ads toAds(CreateAds createAds) {
        Ads ads = new Ads();
        ads.setTitle(createAds.getTitle());
        ads.setPrice(createAds.getPrice());
        ads.setDescription(createAds.getDescription());
        return ads;
    }


}
