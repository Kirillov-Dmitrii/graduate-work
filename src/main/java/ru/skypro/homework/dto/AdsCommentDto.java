package ru.skypro.homework.dto;

import lombok.Data;


@Data
public class AdsCommentDto {

    private String author;

    private String createdAt;

    private Integer pk;

    private String text;
}
