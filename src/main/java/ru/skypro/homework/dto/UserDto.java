package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String firstName;
    @Null
    private Integer id;
    @NotNull
    private String lastName;
    @NotNull
    private String phone;
    @Null
    private String regDate;
    @NotNull
    private String city;
    @NotNull
    private String image;


}
