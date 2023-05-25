package com.vodafone.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer linkId;
    private String rel;
    private String href;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private Article article;
}
