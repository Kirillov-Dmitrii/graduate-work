package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto get() {
        User user = userRepository.findById(new Integer(1)).orElse(null);
        if (user != null) {
            return userMapper.toDto(user);
        } else {
            return null;
        }
    }

    public UserDto set(UserDto userDto) {
        User userFromDB = userRepository.findById(new Integer(1)).orElse(null);
        if (userFromDB != null) {
            User user = userMapper.toEntity(userDto, userFromDB);
            userRepository.save(user);
            userDto.setId(user.getId());
            return userDto;
        } else {
            return null;
        }
    }

}
