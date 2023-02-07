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

    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;

    @ManyToOne
    private Ads ads;
}
