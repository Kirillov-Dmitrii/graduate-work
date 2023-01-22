package ru.skypro.homework.entity;

import javax.persistence.*;

@Entity
@Table(name = "ads_comments")
public class AdsComment {

    private Integer author;

    private String createdAt;
    @Id
    @GeneratedValue(generator = "comment_generator")
    private Integer pk;

    private String text;
    @ManyToOne
    private Ads ads;

}
