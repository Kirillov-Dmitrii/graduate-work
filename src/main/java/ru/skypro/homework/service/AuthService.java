package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;

public interface AuthService extends UserDetailsService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
}
