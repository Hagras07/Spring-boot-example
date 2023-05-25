package com.vodafone.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "article-name")
    private String name;

    private String author;

    @Column(name = "auth-id")
    private Integer authorId;

    @OneToMany(mappedBy = "article")
    private List<Links> links;

}
