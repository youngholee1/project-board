package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
//@DataJpaTest
@SpringBootTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    void contextLoads() {
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorkFine() {

        // Given
        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles).isNotNull().hasSize(123);

    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorkFine() {

        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        // When
        Article saveArticle =  articleRepository.saveAndFlush(article);

        // Then
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorkFine() {

        // Given
        Article article = articleRepository.findById(1L).orElseThrow(); // 첫번째에 해당하는 Long 타입
        long previusArticleCount = articleRepository.count();
        long previusArticleCommentCount = articleCommentRepository.count();
        int deletedCommnetsSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previusArticleCount - 1 );
        assertThat(articleCommentRepository.count()).isEqualTo(previusArticleCommentCount - deletedCommnetsSize );
    }

}