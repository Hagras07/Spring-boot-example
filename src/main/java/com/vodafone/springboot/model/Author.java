package com.vodafone.springboot.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "Author-Table")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;

}
