package com.vodafone.springboot.controller;

import com.vodafone.springboot.model.Author;
import com.vodafone.springboot.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
    @PostMapping(value = "/authors", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Author> addArticle(@RequestBody Author author) {
        author = authorService.addArticle(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
}
