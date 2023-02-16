package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@RestController
@Tag(name = "Пользователь", description = "Управление данными пользователя")
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(
            summary = "Получить данные",
            description = "Выводит данные о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @Secured("ROLE_USER")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto userDto = userService.get();

            return ResponseEntity.ok(userDto);

    }
    @Operation(
            summary = "Установить пароль",
            description = "Позволяет обновить пароль пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = NewPassword.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @Secured("ROLE_USER")
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        return ResponseEntity.ok(authService.setPassword(newPassword, authentication));
    }
    @Operation(
            summary = "Обновить данные",
            description = "Позволяет обновить информацию о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @Secured("ROLE_USER")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto userDtoCopy = userService.set(userDto);
        if (userDtoCopy != null) {
            return ResponseEntity.ok(userDtoCopy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Обновить аватарку",
            description = "Обновить изображение пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @Secured("ROLE_USER")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateUserImage(@RequestParam MultipartFile image) {
        userService.updateImage(image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить аватарку",
            description = "Получить изображение пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    @Secured("ROLE_USER")
    @GetMapping(value = "/me/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    ResponseEntity<byte[]> getUserImage(@PathVariable String id) {
        byte[] data = userService.getImage(id);
        if (data == null) {
            logger.info("image = null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(data);
    }

}
