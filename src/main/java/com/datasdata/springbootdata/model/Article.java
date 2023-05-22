package com.datasdata.springbootdata.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "author-name")
    private String name;
    @Column(name = "hamada")
    private String author;

    private Integer authorId;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Links> links;
}
