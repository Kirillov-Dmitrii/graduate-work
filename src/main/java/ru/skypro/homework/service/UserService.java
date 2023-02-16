package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.mappers.UserPrincipal;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.NoSuchElementException;

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
    @Transactional
    public void updateImage(MultipartFile image) {
        byte[] data;
        try {
            data = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserImage userImage;
        User user;
        if (userImageRepository.existsByUser_Id(1)) {
            userImage = userImageRepository.findByUser_Id(1);
            user = userImage.getUser();
        } else {
            userImage = new UserImage();
            user = userRepository.findById(1).get();
            userImage.setUser(user);
        }
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

}
