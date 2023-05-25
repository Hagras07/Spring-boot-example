package com.vodafone.springboot.service;

import com.vodafone.springboot.controller.ArticlesController;
import com.vodafone.springboot.controller.AuthorController;
import com.vodafone.springboot.model.Article;
import com.vodafone.springboot.model.Links;
import com.vodafone.springboot.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Primary
public class ArticleServiceJpa implements ArticleService{
    @Autowired
    private ArticleRepo articleRepo;

//    public ArticleServiceJpa(ArticleRepo articleRepo) {
//        this.articleRepo = articleRepo;
//    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> article1 = new ArrayList<>();
        List<Article> allArticle = articleRepo.findAll();
        for (Article article : allArticle) {
            article1.add(addLinks(article));
        }

        return article1;
    }

    @Override
    public Article getArticleById(Integer id) {
        Optional<Article> byId = articleRepo.findById(id);

        return addLinks(byId.orElse(null)) ;
    }

    @Override
    public List<Article> getArticlesByAuthorName(String authorName) {

        return articleRepo.findByName(authorName);
    }

    @Override
    public Article addArticle(Article article) {
        Article save = articleRepo.save(article);

        return addLinks(save);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleRepo.deleteById(id);

    }

    @Override
    public Article updateArticle(Integer id, Article article) {
        if (id != null){
            articleRepo.deleteById(id);
        }
        articleRepo.save(article);

        return addLinks(article);
    }


    private Article addLinks(Article article){
        List<Links> links = new ArrayList<>();
        Links self = new Links();

        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(ArticlesController.class)
                .getArticle(article.getId())).withRel("self");

        self.setRel("self");
        self.setHref(selfLink.getHref());

        Links authorLink = new Links();
        Link authLink = WebMvcLinkBuilder.linkTo(methodOn(AuthorController.class)
                .getAuthorById(article.getAuthorId())).withRel("author");
        authorLink.setRel("author");
        authorLink.setHref(authLink.getHref());

        links.add(self);
        links.add(authorLink);
        article.setLinks(links);
        return article;
    }

}
