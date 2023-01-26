package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setCity(user.getCity());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setImage(user.getImage());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRegDate(user.getRegDate());
        return userDto;
    }

    public static User toEntity(UserDto userDto, User user) {
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setImage(userDto.getImage());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRegDate(userDto.getRegDate());
        return user;
    }

}
