package com.everton.StarLog.controllers;

import com.everton.StarLog.entities.Film;
import com.everton.StarLog.entities.FilmsResponse;
import com.everton.StarLog.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {

    @Autowired
    private FilmService service;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public FilmsResponse allFilms(){
        return service.allFilms();
    }

    @ResponseBody
    @PostMapping("/{idClient}/{idFilm}")
    @ResponseStatus(HttpStatus.CREATED)
    public Film likedFilm(@PathVariable("idClient") Long idClient, @PathVariable("idFilm")  Long idFilm)  {
        return  service.likedFilm(idClient, idFilm);
    }

    @ResponseBody
    @GetMapping("/{idClient}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Film> favorite(@PathVariable  ("idClient") Long idClient){
        return service.favorite(idClient);
    }

}
