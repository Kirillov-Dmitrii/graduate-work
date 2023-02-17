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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.Collection;


@RestController
@Tag(name = "Объявления", description = "Работа с объявлениями и комментариями")
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsService adsService;

    private final Logger logger = LoggerFactory.getLogger(AdsController.class);

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @Operation(
            summary = "Получить все объявления",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))) })
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        logger.info("getAllAds");
        return ResponseEntity.ok(adsService.getAll());
    }

    @Operation(
            summary = "Создать новое объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<AdsDto> addAds(@RequestPart("properties") CreateAds createAds, @RequestParam MultipartFile image, Authentication authentication) throws IOException {
        logger.info("addAds");
        if (createAds.getDescription() == null || createAds.getTitle() == null || createAds.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        AdsDto adsDto = adsService.add(createAds, image, authentication.getName());
        logger.info(adsDto.toString());
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(
            summary = "Получить объявления пользователя",
            description = "Позволяет получить все объявления, которые создал пользователь"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication authentication) {
        logger.info("getAdsMe");
        ResponseWrapperAds responseWrapperAds = adsService.getAdsMe(authentication.getName());
        return ResponseEntity.ok(responseWrapperAds);
    }
    @Operation(
            summary = "Посмотреть комментарии",
            description = "Получает все комментарии, которые оставили под объявлением"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAdsComment.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping("/{ad_pk}/comments")
    ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable Integer adPk) {
        logger.info("getAdsComments");
        if (adPk < 0) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<ResponseWrapperAdsComment>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Добавить комментарий к объявлению",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsCommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{ad_pk}/comments")
    ResponseEntity<AdsCommentDto> addComments(@PathVariable Integer adPk, @RequestBody AdsCommentDto adsCommentDto) {
        logger.info("addComments");
        return new ResponseEntity<AdsCommentDto>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Получить объявление",
            description = "Получает объявление со всеми данными о пользователе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = FullAds.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping("/{id}")
    ResponseEntity<FullAds> getFullAd(@PathVariable Integer id) {
        logger.info("getFullAd");
        FullAds fullAds = adsService.get(id);
        if (fullAds == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fullAds);
    }
    @Operation(
            summary = "Удалить объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden") })
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeAds(@PathVariable Integer id, Authentication authentication) {
        logger.info("removeAds ");
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!adsService.remove(id, authentication)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Обновить объявление",
            description = "Позволяет отредактировать объявление"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody CreateAds adsBody, Authentication authentication) {
        logger.info("updateAds");
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsDto adsDto = adsService.update(id, adsBody, authentication);
        if (adsDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adsDto);
    }
    @Operation(
            summary = "Посмотреть комментарий",
            description = "Позволяет просмотреть определенный комментарий к объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsCommentDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<AdsCommentDto> getComments(@PathVariable("adPk") String adPk, @PathVariable("id") Integer id) {
        logger.info("getComments");
        return new ResponseEntity<AdsCommentDto>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Удалить комментарий",
            description = "Удаляет комментарий по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<Void> deleteComments(@PathVariable("adPk") Integer adPk, @PathVariable("id") Integer id) {
        logger.info("deleteComments");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
            summary = "Обновить комментарий",
            description = "Позволяет редактировать комментарий"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsCommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<AdsCommentDto> updateComments(@PathVariable("adPk") Integer adPk, @PathVariable("id") Integer id, @RequestBody AdsCommentDto adsCommentDtoBody) {
        logger.info("updateComments");
        return new ResponseEntity<AdsCommentDto>(HttpStatus.NOT_IMPLEMENTED);
    }
}
