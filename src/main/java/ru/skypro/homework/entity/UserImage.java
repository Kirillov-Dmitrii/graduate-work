package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class UserImage {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private User user;
}
