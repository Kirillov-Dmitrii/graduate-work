package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Null;
import java.util.Collection;

@Data
public class ResponseWrapperAdsComment {
    @Null
    private Integer count;
    @Null
    private Collection<AdsCommentDto> results;

}
