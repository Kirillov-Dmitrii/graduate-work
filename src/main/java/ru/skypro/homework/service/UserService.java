package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto get() {
        User user = userRepository.findById(new Integer(1)).orElse(null);
        if (user != null) {
            return UserMapper.toDto(user);
        } else {
            return null;
        }
    }

    public UserDto set(UserDto userDto) {
        User userFromDB = userRepository.findById(new Integer(1)).orElse(null);
        if (userFromDB != null) {
            User user = UserMapper.toEntity(userDto, userFromDB);
            userRepository.save(user);
            userDto.setId(user.getId());
            return userDto;
        } else {
            return null;
        }
    }

}
