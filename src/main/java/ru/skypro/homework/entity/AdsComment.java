package ru.skypro.homework.entity;

import javax.persistence.*;

@Entity
@Table(name = "ads_comments")
public class AdsComment {

    @Id
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;

    private Integer author;

    private String createdAt;

    private String text;



}
