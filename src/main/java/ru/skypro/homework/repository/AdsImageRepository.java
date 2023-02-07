package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsImage;

import java.util.List;

@Repository
public interface AdsImageRepository extends JpaRepository<AdsImage, Integer> {
    List<AdsImage> findAdsImagesByAds_Pk(Integer adsPk);
}
