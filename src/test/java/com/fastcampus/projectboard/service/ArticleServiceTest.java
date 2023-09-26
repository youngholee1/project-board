package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.dto.ArticleDto;
import static org.mockito.BDDMockito.*;

import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("검색어 없이 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @Disabled
    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnArticle() {
        // Given
//        SearchParameters param = SearchParameters.of(SearchType.TITLE, "search keyword");


        // When
        ArticleDto articles = sut.searchArticle(1L); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.saveArticle( ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content","#java"));
        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAnd_whenSavingArticle_thenSavesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.updateArticle( 1L, ArticleUpdateDto.of("title", "content","#java"));
        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
//        given(articleRepository.delete(any(Article.class))).willReturn(null);
        willDoNothing().given(articleRepository).delete(any(Article.class));
        // When
        sut.deleteArticle( 1L);
        // Then
        then(articleRepository).should().delete(any(Article.class));

    }

}