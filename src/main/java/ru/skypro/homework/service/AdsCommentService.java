package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.component.Authority;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.mappers.AdsCommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdsCommentService {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final AdsCommentMapper adsCommentMapper;
    private final Logger logger = LoggerFactory.getLogger(AdsCommentService.class);

    public AdsCommentService(CommentRepository commentRepository, AdsRepository adsRepository, AdsCommentMapper adsCommentMapper) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.adsCommentMapper = adsCommentMapper;
    }

    public ResponseWrapperAdsComment getAdsComments(Integer adsId) {
        logger.info("getAdsComments invoked");
        ResponseWrapperAdsComment responseWrapperAdsComment;

        List<AdsComment> allAdsComments = commentRepository.findByAds_Pk(adsId);



        List<AdsCommentDto> adsCommentDtoList = new ArrayList<>();

        allAdsComments.forEach(adsComment -> {
            AdsCommentDto adsCommentDto;
            adsCommentDto = adsCommentMapper.toDto(adsComment);
            adsCommentDtoList.add(adsCommentDto);
        });
        responseWrapperAdsComment = adsCommentMapper.toResponseWrapperAdsComment(adsCommentDtoList);

        return responseWrapperAdsComment;
    }

    public AdsCommentDto getComment(Integer adsPk, Integer id) {
        AdsComment comment = commentRepository.findByPkAndAds_Pk(id, adsPk);
        AdsCommentDto commentDto = adsCommentMapper.toDto(comment);
        return commentDto;
    }

    public void addComments(Integer adsId, AdsCommentDto commentDto) {
        Date date = Date.from(Instant.now());
        AdsComment adsComment = adsCommentMapper.toEntity(commentDto);
        adsComment.setAuthor(777);
        adsComment.setCreatedAt(date.toString());

        Ads ads = adsRepository.findByPk(adsId);
        adsComment.setAds(ads);
        commentRepository.save(adsComment);
    }

    @Transactional
    public void deleteComment(Integer id) {
//        if (Authority.check(authentication).equals(Role.ADMIN.toString()) ||
//                (Authority.check(authentication).equals(Role.USER.toString())
//                        && adsRepository.existsByPkAndUser_Username(id, authentication.getName()))) {
//            commentRepository.deleteById(id);
//        }
        commentRepository.deleteById(id);
    }


    @Transactional
    public AdsCommentDto updateComment(Integer adPk, Integer id, AdsCommentDto commentDto) {
        logger.info("updateCommentsService");
        AdsComment adsComment = commentRepository.findByPkAndAds_Pk(id, adPk);
        adsComment.setText(commentDto.getText());
        commentRepository.save(adsComment);
        AdsCommentDto newCommentDto = adsCommentMapper.toDto(adsComment);
        return newCommentDto;
    }
}
