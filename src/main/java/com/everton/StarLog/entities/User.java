package com.everton.StarLog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Size(min = 3,message = "Name must be at least 3 characters long")
    @NotBlank(message = "name must not be blank")
    private String name;


    @CPF(message = "Invalid CPF number")
    @NotBlank(message = "CPF must not be blank")
    @Column(nullable = false, unique = true)
    private String cpf;


    @Email(message = "Invalid Email")
    @NotBlank(message = "Email must not be blank")
    private String email;



    @ManyToMany
    @JoinTable(
            name = "user_favorite_films",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
           @JsonIgnore
    Set<Film> favoritesFilms = new HashSet<>();
}
