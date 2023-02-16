package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ads_comments")
public class AdsComment {

    @Id
    @GeneratedValue
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;

    private Integer author;

    private String createdAt;

    private String text;

}
