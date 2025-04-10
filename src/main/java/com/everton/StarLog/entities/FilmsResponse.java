package com.everton.StarLog.entities;

import com.everton.StarLog.dtos.FilmDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmsResponse {

    private int count;
    private String next;
    private String previous;
    private List<FilmDto> results;
}
