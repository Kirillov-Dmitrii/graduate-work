package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "ads")
public class Ads {

    private Integer price;

    private String description;
    @Id
    @GeneratedValue(generator = "ads_generator")
    private Integer pk;

    private String title;
    @ManyToOne
    private User user;

    @OneToMany
    private List<AdsImage> adsImage;

}
