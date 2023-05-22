package com.datasdata.springbootdata.service;

import com.datasdata.springbootdata.model.Article;
import com.datasdata.springbootdata.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        return articleRepo.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        Optional<Article> byId = articleRepo.findById(id);
        Iterator<Article> iterator = byId.stream().iterator();
        return  iterator.next();
    }

    @Override
    public List<Article> getArticlesByAuthorName(String authorName) {
        return articleRepo.findByName(authorName);
    }

    @Override
    public Article addArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleRepo.deleteById(id);

    }

    @Override
    public Article updateArticle(Integer id, Article article) {
        if (id != null ){
            articleRepo.deleteById(id);
        }
        return articleRepo.save(article);
    }
}
