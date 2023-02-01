package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ads_images")
@Data
public class AdsImage {
    @Id
    @GeneratedValue(generator = "ads_image_generator")
    private String id;

    private String image;


}
