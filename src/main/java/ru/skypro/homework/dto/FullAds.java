package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.List;

@Data
public class FullAds {
    @Null
    private String authorFirstName;
    @Null
    private String authorLastName;
    @Null
    private String description;
    @Null
    private String email;
    @Null
    private List<String> image;
    @Null
    private String phone;
    @Null
    private Integer pk;
    @Null
    private Integer price;
    @Null
    private String title;

}
