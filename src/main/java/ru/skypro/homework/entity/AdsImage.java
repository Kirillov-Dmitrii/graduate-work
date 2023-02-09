package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ads_images")
@Data
public class AdsImage {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;

    @ManyToOne
    private Ads ads;
}
