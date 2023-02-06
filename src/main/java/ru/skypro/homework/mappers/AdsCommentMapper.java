package ru.skypro.homework.mappers;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.entity.AdsComment;

@Service
public class AdsCommentMapper {

    public AdsCommentDto toDto(AdsComment entity) {
        AdsCommentDto dto = new AdsCommentDto();
        dto.setPk(entity.getPk());
        dto.setText(entity.getText());
        dto.setAuthor(entity.getAuthor());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public AdsComment toEntity(AdsCommentDto dto) {
        AdsComment entity = new AdsComment();
        entity.setPk(dto.getPk());
        entity.setText(dto.getText());
        entity.setAuthor(dto.getAuthor());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
