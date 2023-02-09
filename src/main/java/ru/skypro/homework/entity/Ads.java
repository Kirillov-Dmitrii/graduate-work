package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer price;

    private String description;

    private String title;

}
