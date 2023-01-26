package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String regDate;

    private String city;

    private String image;

    private String username;

    private String password;

    private Role role;

}
