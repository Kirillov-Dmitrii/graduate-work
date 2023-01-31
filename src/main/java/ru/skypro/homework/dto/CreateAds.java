package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CreateAds {
    @NotNull
    private Integer price;
    @NotNull
    private String description;
    @NotNull
    private String title;

}
