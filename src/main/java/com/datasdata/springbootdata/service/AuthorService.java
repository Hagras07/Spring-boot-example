package com.datasdata.springbootdata.service;


import com.datasdata.springbootdata.model.Article;
import com.datasdata.springbootdata.model.Author;

public interface AuthorService {
    Author getAuthorById(Integer id);
    Author addArticle(Author article);

}
