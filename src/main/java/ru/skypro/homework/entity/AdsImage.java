package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "ads_images")
@Data
public class AdsImage {
    @Id
    private String id;

    private Long fileSize;

    private String mediaType;

    private byte[] data;
    @ManyToOne
    private Ads ads;


}
