package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, String> {

    UserImage findByUser_Id(Integer id);

    Boolean existsByUser_Id(Integer id);

}
