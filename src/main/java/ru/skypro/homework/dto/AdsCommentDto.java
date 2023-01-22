package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class AdsCommentDto {
    @Null
    private Integer author;
    @Null
    private String createdAt;
    @Null
    private Integer pk;
    @NotNull
    private String text;

}
