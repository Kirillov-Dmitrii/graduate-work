package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsImage;

import java.util.List;

@Repository
public interface AdsImageRepository extends JpaRepository<AdsImage, String> {
    List<AdsImage> findAdsImagesByAds_Pk(Integer adsPk);

    AdsImage findByAds_Pk(Integer adPk);

    boolean existsByAds_PkAndAds_User_Username(Integer adsPk, String userName);

}
