package com.everton.StarLog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private int episodeId;
    @Column(columnDefinition = "TEXT")
    private String openingCrawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;


    @ManyToMany(mappedBy = "favoritesFilms")
    @JsonBackReference
    Set<User> users = new HashSet<>();


}
