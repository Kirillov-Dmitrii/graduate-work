package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.Ads;

import java.util.Collection;
import java.util.List;

@Component
public class AdsMapper {

    public static ResponseWrapperAds toResponseWrapperAds(Collection<AdsDto> adsDtoCollection) {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(adsDtoCollection.size());
        responseWrapperAds.setResults(adsDtoCollection);
        return responseWrapperAds;
    }

    public static AdsDto toAdsDto(Ads ads, List<String> adsImageCollection) {
        AdsDto adsDto = new AdsDto();
        adsDto.setAuthor(ads.getUser().getId());
        adsDto.setPk(ads.getPk());
        adsDto.setImage(adsImageCollection);
        adsDto.setTitle(ads.getTitle());
        adsDto.setPrice(ads.getPrice());
        return adsDto;
    }

    public static Ads toAds(AdsDto adsDto) {
        Ads ads = new Ads();
        ads.setTitle(adsDto.getTitle());
        ads.setPk(adsDto.getPk());
        ads.setImage(adsDto.getImage().get(0));
        ads.setPrice(adsDto.getPrice());
        ads.getUser().setId(adsDto.getAuthor());
        return ads;
    }

}
