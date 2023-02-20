package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserImageRepository userImageRepository;

    private final UserMapper userMapper;



    public UserService(UserRepository userRepository, UserMapper userMapper,
                       UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userImageRepository = userImageRepository;
    }

    public UserDto get(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            return userMapper.toDto(user);
        } else {
            return null;
        }
    }

    public UserDto set(UserDto userDto, String userName) {
        User userFromDB = userRepository.findByUsername(userName);
        if (userFromDB != null) {
            User user = userMapper.toEntity(userDto, userFromDB);
            userRepository.save(user);
            userDto.setId(userFromDB.getId());
            return userDto;
        } else {
            return null;
        }
    }
    @Transactional
    public void updateImage(MultipartFile image, String userName) {
        byte[] data;
        try {
            data = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserImage userImage = userImageRepository.findByUser_Username(userName);
        if (userImage == null) {
            userImage = new UserImage();
        }
        User user = userRepository.findByUsername(userName);
        userImage.setUser(user);
        userImage.setData(data);
        userImage.setFileSize(image.getSize());
        userImage.setMediaType(image.getOriginalFilename().substring(image.getOriginalFilename().indexOf(".") + 1));
        UserImage savedUserIamge = userImageRepository.save(userImage);
        user.setImage("/users/me/image/" + savedUserIamge.getId());
        userRepository.save(user);
    }

    public byte[] getImage(String id) {
        if (userImageRepository.existsById(id)) {
            return userImageRepository.findById(id).get().getData();
        }
        return null;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserFromDB(String userName) {
        return userRepository.findByUsername(userName);
    }
}
