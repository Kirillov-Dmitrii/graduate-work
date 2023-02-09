package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "ads_images")
@Data
public class AdsImage {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Long fileSize;

    private String mediaType;
    @Lob
    private byte[] data;
    @ManyToOne
    private Ads ads;


}
