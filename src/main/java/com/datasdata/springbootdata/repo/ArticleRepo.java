package com.datasdata.springbootdata.repo;

import com.datasdata.springbootdata.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo  extends JpaRepository<Article,Integer> {
//    Article findByArticleId(@Param(value = "id") int id);
    List<Article> findByName(String authorName);
//    boolean existsArticleWithId(Integer id);

}
