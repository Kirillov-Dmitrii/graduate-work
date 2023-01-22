package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAdsComment {

    private Integer count;

    private Collection<AdsComment> results;

}
