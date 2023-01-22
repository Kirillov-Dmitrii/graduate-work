package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class FullAds {

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private Collection<String> image;

    private String phone;

    private Integer pk;

    private Integer price;

    private String title;

}
