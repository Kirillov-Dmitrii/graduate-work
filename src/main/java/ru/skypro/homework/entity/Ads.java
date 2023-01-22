package ru.skypro.homework.entity;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ads {

    private String image;

    private Integer price;

    private String description;
    @Id
    @GeneratedValue(generator = "ads_generator")
    private Integer pk;

    private String title;
    @ManyToOne
    private User user;

}
