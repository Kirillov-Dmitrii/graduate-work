package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsComment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<AdsComment, Integer> {
    List<AdsComment> findByAds_Pk(Integer adsId);

    boolean existsByPkAndAuthor(Integer pk, String author);

}
