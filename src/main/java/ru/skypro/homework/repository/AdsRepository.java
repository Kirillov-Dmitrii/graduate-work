package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ads;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    List<Ads> findAllByUser_Username(String userName);

    boolean existsByPkAndUser_Username(Integer pk, String userName);

    Ads findByPk(Integer pk);
}
