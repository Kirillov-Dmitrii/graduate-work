package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CreateAds {
    @NotNull
    private String image;
    @NotNull
    private Integer price;
    @NotNull
    private String description;
    @Null
    private Integer pk;
    @NotNull
    private String title;

}
