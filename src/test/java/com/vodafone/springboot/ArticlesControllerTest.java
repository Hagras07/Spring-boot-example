package com.vodafone.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.springboot.model.Article;
import com.vodafone.springboot.model.Links;
import com.vodafone.springboot.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.swing.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArticlesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleService articleService;

    private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    void getArticles() throws Exception {
        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());
        Article article2 = new Article(2,"article2","Ibrahem",2, new ArrayList<>());
        List<Article> articleList = Arrays.asList(article1,article2);

        given(articleService.getAllArticles()).willReturn(articleList);

        mockMvc.perform(get("/v1/articles").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect((jsonPath("$[0].name", equalTo(article1.getName()))))
                .andExpect(status().isOk());
    }

    @Test
    void getArticle() throws Exception {
        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());

        when(articleService.getArticleById(1)).thenReturn(article1);


        mockMvc.perform(get("/v1/articles/1").contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andExpect(jsonPath("$.name").value("article1"));

        verify(articleService, times(1)).getArticleById(anyInt());
        verifyNoMoreInteractions(articleService);
    }

    @Test
    void addArticle() throws Exception {

        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());
        when(articleService.addArticle(any(Article.class))).thenReturn(article1);

        mockMvc.perform(post("/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("article1"));

    }

    @Test
    void updateArticle() throws Exception {

        Article article1 = new Article(1,"article1","Karim",1, new ArrayList<>());

        when(articleService.updateArticle(eq(1), any(Article.class))).thenReturn(article1);

        mockMvc.perform(post("/v1/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(article1)));

        article1.setName("articleWithNameChange");

        mockMvc.perform(put("/v1/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("articleWithNameChange"));
    }

    @Test
    void deleteArticle() throws Exception{

        doNothing().when(articleService).deleteArticle(1);

        mockMvc.perform(delete("/v1/articles/1"))
                .andExpect(status().isNoContent());
    }
}
