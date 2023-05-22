package com.datasdata.springbootdata.repo;

import com.datasdata.springbootdata.model.Article;
import com.datasdata.springbootdata.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {

}
