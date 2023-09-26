package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.repository.ArticleRepository;
import com.fastcampus.projectboard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword, Pageable pageable) {
//        if(search_keyword == null || search_keyword.isBlank()) {
//            return articleRepository.findAll(pageable).map();
//        }
        return Page.empty();
    };

//    @Transactional(readOnly = true)
//    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
//        if (searchKeyword == null || searchKeyword.isBlank()) {
//            return articleRepository.findAll(pageable).map(ArticleDto::from);
//        }
//
//        return switch (searchType) {
//            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
//            case HASHTAG -> articleRepository.findByHashtagNames(
//                            Arrays.stream(searchKeyword.split(" ")).toList(),
//                            pageable
//                    )
//                    .map(ArticleDto::from);
//        };
//    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long articleId, ArticleUpdateDto dto) {
    }

    public void deleteArticle(long articleId) {
    }
}
