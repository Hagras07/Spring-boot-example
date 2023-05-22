package com.datasdata.springbootdata.service;

import com.datasdata.springbootdata.contoller.ArticlesController;
import com.datasdata.springbootdata.contoller.AuthorController;
import com.datasdata.springbootdata.model.Article;
import com.datasdata.springbootdata.model.Links;
import com.datasdata.springbootdata.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Primary
public class ArticleJpaServices implements ArticleService{
    @Autowired
    private final ArticleRepo articleRepo;

    public ArticleJpaServices(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

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

        Link selfLink = linkTo(methodOn(ArticlesController.class)
                .getArticle(article.getId())).withRel("self");

        self.setRel("self");
        self.setHref(selfLink.getHref());

        Links authorLink = new Links();
        Link authLink = linkTo(methodOn(AuthorController.class)
                .getAuthorById(article.getAuthorId())).withRel("author");
        authorLink.setRel("author");
        authorLink.setHref(authLink.getHref());

        links.add(self);
        links.add(authorLink);
        article.setLinks(links);
        return article;
    }

}
