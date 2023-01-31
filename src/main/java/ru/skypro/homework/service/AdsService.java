package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.AdsMapper;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsService {

    private AdsRepository adsRepository;

    private AdsImageRepository adsImageRepository;

    private UserService userService;

    public AdsService(AdsRepository adsRepository, AdsImageRepository adsImageRepository, UserService userService) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
        this.userService = userService;
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

    public AdsDto add(CreateAds createAds, MultipartFile image) {
        Ads ads = AdsMapper.toAds(createAds);
        adsRepository.save(ads);
        return AdsMapper.toAdsDto(ads);
    }

    public ResponseWrapperAds getAdsMe(Boolean authenticated , String authorities0Authority, Object credentials, Object details, Object principal) {
        List<Ads> adsList = adsRepository.findAll();
        Collection<AdsDto> adsDtoCollection = Collections.emptyList();
        if (!adsList.isEmpty()) {
            adsList.forEach(ads -> {
                AdsDto adsDto = new AdsDto();
                AdsMapper.toAdsDto(ads);
                adsDtoCollection.add(adsDto);
            });
        }
        return AdsMapper.toResponseWrapperAds(adsDtoCollection);
    }

    public FullAds get(Integer id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        UserDto userDto = userService.get();
        if (ads != null) {
            FullAds fullAds = new FullAds();
            fullAds.setPk(ads.getPk());
            fullAds.setImage(ads.getAdsImage().stream().map(e -> e.getImage()).collect(Collectors.toList()));
            fullAds.setEmail(userDto.getEmail());
            fullAds.setPhone(userDto.getPhone());
            fullAds.setDescription(ads.getDescription());
            fullAds.setPrice(ads.getPrice());
            fullAds.setTitle(ads.getTitle());
            fullAds.setAuthorFirstName(userDto.getFirstName());
            fullAds.setAuthorLastName(userDto.getLastName());
            return fullAds;
        }
        return null;
    }

    public Boolean remove(Integer id) {
        Boolean exists = adsRepository.existsById(id);
        if (exists) {
            adsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AdsDto update(Integer id, CreateAds createAds) {
        Ads ads = adsRepository.findById(id).orElse(null);
        if (ads != null) {
            ads.setDescription(createAds.getDescription());
            ads.setPrice(createAds.getPrice());
            ads.setTitle(createAds.getTitle());
            adsRepository.save(ads);
            return AdsMapper.toAdsDto(ads);
        }
        return null;
    }
}
