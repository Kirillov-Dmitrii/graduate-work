package ru.skypro.homework.component;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Role;

public class Authority {
    public static String check(Authentication authentication) {
        return authentication.getAuthorities().stream().map(e -> e.getAuthority())
                .filter(e -> e.equals(Role.ADMIN.toString())).findFirst().orElse(Role.USER.toString());
    }
}
