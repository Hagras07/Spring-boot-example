package com.datasdata.springbootdata.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rel;
    private String href;

    @ManyToOne
    @JoinColumn(name = "id")
    private Article article;
}
