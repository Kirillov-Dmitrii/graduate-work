package ru.skypro.homework.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ads")
public class Ads {
    @Id
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String image;

    private Integer price;

    private String description;

    private String title;

}
