package com.fastcampus.projectboard.service;

import com.fastcampus.dto.ArticleDto;
import com.fastcampus.projectboard.domain.type.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleService articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword) {
        return Page.empty();
    };

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long l) {
        return null;
    }

}
