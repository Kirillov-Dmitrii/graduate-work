package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@RestController
@Tag(name = "Объявления", description = "Работа с объявлениями и комментариями")
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    @Operation(
            summary = "Получить все объявления",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))) })
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return new ResponseEntity<ResponseWrapperAds>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Создать новое объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PostMapping
    ResponseEntity<AdsDto> addAds(@RequestBody CreateAds createAds) {
        return new ResponseEntity<AdsDto>(HttpStatus.NOT_IMPLEMENTED);
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
    @GetMapping("/me")
    ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestParam(value = "authenticated") Boolean authenticated,
                                                @RequestParam(value = "authorities[0].authority") String authorities0Authority,
                                                @RequestParam(value = "credentials") Object credentials,
                                                @RequestParam(value = "details") Object details,
                                                @RequestParam(value = "principal") Object principal) {
        return new ResponseEntity<ResponseWrapperAds>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Посмотреть комментарии",
            description = "Получает все комментарии, которые оставили под объявлением"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAdsComment.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping("/{ad_pk}/comments")
    ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable String ad_pk) {
        /* Не уловил тут смысл, почему ad_pk String, если в dto он Integer */
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
    @PostMapping("/{ad_pk}/comments")
    ResponseEntity<AdsCommentDto> addComments(@PathVariable String ad_pk, @RequestBody AdsCommentDto adsCommentDto) {
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
        return new ResponseEntity<FullAds>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Удалить объявление",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden") })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeAds(@PathVariable Integer id) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
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
    @PatchMapping("/{id}")
    ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody CreateAds adsBody) {
        return new ResponseEntity<AdsDto>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Operation(
            summary = "Посмотреть комментарий",
            description = "Позволяет просмотреть определенный комментарий к объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsCommentDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @GetMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<AdsCommentDto> getComments(@PathVariable("ad_pk") String ad_pk, @PathVariable("id") Integer id) {
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
    @DeleteMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") String ad_pk, @PathVariable("id") Integer id) {
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

            @ApiResponse(responseCode = "404", description = "Not Found") })
    @PatchMapping("/{ad_pk}/comments/{id}")
    ResponseEntity<AdsCommentDto> updateComments(@PathVariable("ad_pk") String ad_pk, @PathVariable("id") Integer id, @RequestBody AdsCommentDto adsCommentDtoBody) {
        return new ResponseEntity<AdsCommentDto>(HttpStatus.NOT_IMPLEMENTED);
    }
}
