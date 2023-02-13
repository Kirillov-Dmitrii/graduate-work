package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;

    private final PasswordEncoder encoder;

    private UserRepository userRepository;

    private final UserService userService;

    public AuthServiceImpl(UserDetailsManager manager, UserRepository userRepository, UserService userService) {
        this.manager = manager;
        this.encoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.skypro.homework.entity.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public boolean login(String userName, String password) {
/*        if (!manager.userExists(userName)) {
            return false;
        }*/
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(userName);
        } catch (UsernameNotFoundException e) {
            return false;
        }
/*        UserDetails userDetails = manager.loadUserByUsername(userName);*/
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
/*        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }*/
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(registerReq.getUsername());
        } catch (UsernameNotFoundException e) {
            UserDetails userToRegister = User.builder()
                    .username(registerReq.getUsername())
                    .password(encoder.encode(registerReq.getPassword()))
                    .roles(role.name()).build();
            ru.skypro.homework.entity.User newUser = new ru.skypro.homework.entity.User();
            newUser.setUsername(userToRegister.getUsername());
            newUser.setPassword(userToRegister.getPassword());
            newUser.setRole(role);
            userService.save(newUser);
            return true;
        }
/*        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReq.getPassword())
                        .username(registerReq.getUsername())
                        .roles(role.name())
                        .build()
        );*/
        return false;
    }

}
