package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AdsImageService;

import java.io.IOException;
import java.util.Collection;

@RestController
@Tag(name = "Аватарка", description = "Работа с изображениями объявлений")
@RequestMapping("/")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final AdsImageService adsImageService;

    private final Logger logger = LoggerFactory.getLogger(AdsController.class);

    public ImageController(AdsImageService adsImageService) {
        this.adsImageService = adsImageService;
    }

    @Operation(
            summary = "Обновить изображение",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/ads/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<byte[]> updateImage(@PathVariable Integer id, @RequestParam MultipartFile image, Authentication authentication) {
        logger.info("updateImage");
        byte[] data = adsImageService.update(id, image, authentication);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(data);
    }
    @Operation(
            summary = "Получить изображение",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        byte[] data = adsImageService.get(id);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(data);
    }
}
