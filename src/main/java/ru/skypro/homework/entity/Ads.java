package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
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
