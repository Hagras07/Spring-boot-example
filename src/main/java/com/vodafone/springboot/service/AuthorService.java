package com.vodafone.springboot.service;


import com.vodafone.springboot.model.Author;

public interface AuthorService {
    Author getAuthorById(Integer id);
    Author addArticle(Author article);

}
