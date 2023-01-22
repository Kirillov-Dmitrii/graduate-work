package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class AdsDto {
    @Null
    private Integer author;
    @NotNull
    private List<String> image;
    @Null
    private Integer pk;
    @NotNull
    private Integer price;
    @NotNull
    private String title;

}
