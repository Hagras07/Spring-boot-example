package com.datasdata.springbootdata.service;

import com.datasdata.springbootdata.model.Author;
import com.datasdata.springbootdata.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class AuthorJpaServices implements AuthorService  {

    @Autowired
   private final AuthorRepo authorRepo;

    public AuthorJpaServices(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getAuthorById(Integer id) {
        Optional<Author> byId = authorRepo.findById(id);

        return byId.orElse(null) ;
    }

    @Override
    public Author addArticle(Author article) {
        return authorRepo.save(article);
    }
}
