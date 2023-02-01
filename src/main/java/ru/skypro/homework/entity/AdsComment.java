package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ads_comments")
public class AdsComment {
    @ManyToOne
    private User author;

    private String createdAt;
    @Id
    @GeneratedValue(generator = "comment_generator")
    private Integer pk;

    private String text;
    @ManyToOne
    private Ads ads;

}
