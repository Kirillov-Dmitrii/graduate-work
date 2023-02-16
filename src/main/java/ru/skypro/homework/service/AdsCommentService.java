package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.mappers.AdsCommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
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


        if(!allAdsComments.isEmpty()) {
            List<AdsCommentDto> adsCommentDtoList = new ArrayList<>();

            allAdsComments.forEach(adsComment -> {
                AdsCommentDto adsCommentDto;
                adsCommentDto = adsCommentMapper.toDto(adsComment);
                adsCommentDtoList.add(adsCommentDto);
            });
            responseWrapperAdsComment = adsCommentMapper.toResponseWrapperAdsComment(adsCommentDtoList);
        } else {
            responseWrapperAdsComment = new ResponseWrapperAdsComment();
        }
        return responseWrapperAdsComment;
    }

    public void adsComments(Integer adsId, AdsCommentDto commentDto) {
        AdsComment adsComment = adsCommentMapper.toEntity(commentDto);
        Ads ads = adsRepository.findByPk(adsId);
        adsComment.setAds(ads);
        commentRepository.save(adsComment);
    }
}
