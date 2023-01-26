package ru.skypro.homework.mappers;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setCity(user.getCity());
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setImage(user.getImage());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRegDate(user.getRegDate());
        return dto;
    }

    public User toEntity(UserDto userDto) {
        User entity = new User();
        entity.setCity(userDto.getCity());
        entity.setId(userDto.getId());
        entity.setEmail(userDto.getEmail());
        entity.setPhone(userDto.getPhone());
        entity.setImage(userDto.getImage());
        entity.setFirstName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setRegDate(userDto.getRegDate());
        return entity;
    }
}
