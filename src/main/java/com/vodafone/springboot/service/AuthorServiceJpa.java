package com.vodafone.springboot.service;

import com.vodafone.springboot.model.Author;
import com.vodafone.springboot.repo.AuthorRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class AuthorServiceJpa implements AuthorService  {
   private final AuthorRepo authorRepo;

    public AuthorServiceJpa(AuthorRepo authorRepo) {
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
