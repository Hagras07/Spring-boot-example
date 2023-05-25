package com.vodafone.springboot;

import com.vodafone.springboot.model.Article;
import com.vodafone.springboot.repo.ArticleRepo;
import com.vodafone.springboot.service.ArticleService;
import com.vodafone.springboot.service.ArticleServiceJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class ArticleServiceJpaTest{

    @MockBean
    private ArticleRepo articleRepo;

    @Autowired
    private ArticleServiceJpa articleServiceJpa;

    @TestConfiguration
    static class articleServiceContextConfiguration{
        @Bean
        public ArticleService articleService(){
            return new ArticleServiceJpa();
        }
    }

    @Test
    public void getAllArticlesTest_ReturnArticleList(){

        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> data = Arrays.asList(article1, article2);

        given(articleRepo.findAll()).willReturn(data);

        assertThat(articleServiceJpa.getAllArticles())
                .hasSize(2)
                .contains(article1, article2);
    }

    @Test
    public void getArticleByIdTest_ReturnArticleWithSpecifiedId(){

        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());

        given(articleRepo.findById(article1.getId())).willReturn(Optional.of(article1));

        Article result = articleServiceJpa.getArticleById(1);

        assertThat(result.getId()).isEqualTo(article1.getId());
        assertThat(result.getName()).isEqualTo("article1");
        verify(articleRepo, times(1)).findById(article1.getId());
    }

    @Test
    public void getArticlesByAuthorNameTest_ReturnArticlesWithSpecifiedAuthorName(){

        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());
        Article article2 = new Article(2,"article2","Karim",1, new ArrayList<>());
        List<Article> articleList = Arrays.asList(article1,article2);

        when(articleRepo.findByName("Karim")).thenReturn(articleList);

        List<Article> result  = articleServiceJpa.getArticlesByAuthorName("Karim");

        assertThat(result)
                .hasSize(2)
                .contains(article1,article2);
        assertThat(result.get(0).getAuthor()).isEqualTo(article1.getAuthor());
        assertThat(result.get(1).getAuthor()).isEqualTo(article2.getAuthor());
    }
//
//    @Test
//    public void getArticlesByAuthorNameTest_ReturnArticlesWithSpecifiedAuthorName(){
//
//        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());
//        Article article2 = new Article(2,"article2","Karim",1, new ArrayList<>());
//        List<Article> articleList = Arrays.asList(article1,article2);
//
//        when(articleRepo.findByName("Karim")).thenReturn(articleList);
//
//        List<Article> result  = articleServiceJpa.getArticlesByAuthorName("Karim");
//
//        assertThat(result)
//                .hasSize(2)
//                .contains(article1,article2);
//        assertThat(result.get(0).getAuthor()).isEqualTo(article1.getAuthor());
//        assertThat(result.get(1).getAuthor()).isEqualTo(article2.getAuthor());
//    }
}