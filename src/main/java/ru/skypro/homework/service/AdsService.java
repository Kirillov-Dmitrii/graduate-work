package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsImage;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.AdsMapper;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdsService {

    private final AdsRepository adsRepository;

    private final AdsImageRepository adsImageRepository;

    private final UserService userService;

    private final AdsMapper adsMapper;

    private final UserMapper userMapper;

    public AdsService(AdsRepository adsRepository, AdsImageRepository adsImageRepository,
                      UserService userService, AdsMapper adsMapper,
                      UserMapper userMapper) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
        this.userService = userService;
        this.adsMapper = adsMapper;
        this.userMapper = userMapper;
    }
    @Transactional
    public ResponseWrapperAds getAll() {
        List<Ads> allAds = adsRepository.findAll();
        ResponseWrapperAds responseWrapperAds;
        List<AdsDto> adsDtoCollection = new LinkedList<>();
        if (!allAds.isEmpty()) {
            allAds.forEach(ads -> {
                List<String> adsImages = adsImageRepository.findAdsImagesByAds_Pk(ads.getPk()).
                        stream().
                        map(e -> e.getId()).
                        map(e -> "/image/" + e).
                        collect(Collectors.toList());
                AdsDto adsDto = adsMapper.toAdsDto(ads);
                adsDto.setImage(adsImages);
                adsDtoCollection.add(adsDto);
            });}
        responseWrapperAds = adsMapper.toResponseWrapperAds(adsDtoCollection);
        return responseWrapperAds;
    }

    public AdsDto add(CreateAds createAds, MultipartFile image, String userName) {
        AdsImage adsImage = new AdsImage();
        try {
            adsImage.setData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        adsImage.setFileSize(image.getSize());
        adsImage.setMediaType(image.getOriginalFilename().substring(image.getOriginalFilename().indexOf(".") + 1));
        User user = userService.getUserFromDB(userName);
        Ads ads = adsMapper.toAds(createAds);
        ads.setUser(user);
        adsImage.setAds(adsRepository.save(ads));
        adsImageRepository.save(adsImage);
        return adsMapper.toAdsDto(ads);
    }
    @Transactional
    public ResponseWrapperAds getAdsMe(String userName) {
        List<Ads> adsList = adsRepository.findAllByUser_Username(userName);
        List<AdsDto> adsDtoCollection = new LinkedList<>();
        if (!adsList.isEmpty()) {
            adsList.forEach(ads -> {
                List<String> adsImages = adsImageRepository.findAdsImagesByAds_Pk(ads.getPk()).
                        stream().map(e -> e.getId())
                        .map(e -> "/image/" + e)
                        .collect(Collectors.toList());
                AdsDto adsDto = adsMapper.toAdsDto(ads);
                adsDto.setImage(adsImages);
                adsDtoCollection.add(adsDto);
            });
        }
        return adsMapper.toResponseWrapperAds(adsDtoCollection);
    }
    @Transactional
    public FullAds get(Integer id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        if (ads != null) {
            UserDto userDto = userService.get(ads.getUser().getUsername());
            List<AdsImage> adsImages = adsImageRepository.findAdsImagesByAds_Pk(ads.getPk());
            FullAds fullAds = new FullAds();
            fullAds.setPk(ads.getPk());
            fullAds.setImage(adsImages.stream().map(e -> e.getId())
                    .map(e -> "/image/" + e).collect(Collectors.toList()));
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
    @Transactional
    public Boolean remove(Integer id) {
        Boolean exists = adsRepository.existsById(id);
        if (exists) {
            List<AdsImage> adsImages = adsImageRepository.findAdsImagesByAds_Pk(id);
            adsImages.forEach(adsImage -> {
                adsImageRepository.deleteById(adsImage.getId());
            });
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
            return adsMapper.toAdsDto(ads);
        }
        return null;
    }
}
