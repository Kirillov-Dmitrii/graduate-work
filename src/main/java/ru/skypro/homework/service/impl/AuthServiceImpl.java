package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mappers.UserPrincipal;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder encoder;

    private UserRepository userRepository;

    private final UserService userService;

    public AuthServiceImpl(UserRepository userRepository, UserService userService) {
        this.encoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.skypro.homework.entity.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(userName);
        } catch (UsernameNotFoundException e) {
            return false;
        }
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        try {
            loadUserByUsername(registerReq.getUsername());
        } catch (UsernameNotFoundException e) {
            UserDetails userToRegister = User.builder()
                    .username(registerReq.getUsername())
                    .password(registerReq.getPassword())
                    .roles(role.name()).build();
            ru.skypro.homework.entity.User newUser = new ru.skypro.homework.entity.User();
            newUser.setUsername(userToRegister.getUsername());
            newUser.setPassword(encoder.encode(userToRegister.getPassword()));
            newUser.setRole(role);
            userService.save(newUser);
            return true;
        }
        return false;
    }

    public NewPassword setPassword(NewPassword newPassword, Authentication authentication)  {
        NewPassword resultPassword = new NewPassword();
        UserDetails userDetails = loadUserByUsername(authentication.getName());
        if (encoder.matches(newPassword.getCurrentPassword(), userDetails.getPassword())) {
            resultPassword.setNewPassword(newPassword.getNewPassword());
            ru.skypro.homework.entity.User user = userRepository.findByUsername(userDetails.getUsername());
            user.setPassword(encoder.encode(newPassword.getNewPassword()));
            userRepository.save(user);
        }
        return resultPassword;
    }


}
