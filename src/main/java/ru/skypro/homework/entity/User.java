package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String regDate;

    private String city;

    private String image;

    private String username;

    private String password;
    @Column(name = "role")
    private Role role;

}
