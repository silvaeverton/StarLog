package com.everton.StarLog.services;

import com.everton.StarLog.entities.Film;
import com.everton.StarLog.entities.FilmsResponse;
import com.everton.StarLog.entities.User;
import com.everton.StarLog.exceptions.StarLogException;
import com.everton.StarLog.repositories.FilmRepository;
import com.everton.StarLog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    @Autowired
    private FilmRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public FilmsResponse allFilms() {

        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString("https://swapi.dev/api/")
                .pathSegment("films");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<FilmsResponse> response = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                requestEntity,
                FilmsResponse.class
        );

        return response.getBody();

    }

  @Transactional
    public Film likedFilm(Long idClient, Long idFilm) {
      User user = userService.findUserById(idClient);


      Optional<Film> existingFilm = repository.findById(idFilm);

      Film film;
      if (existingFilm.isPresent()) {
          film = existingFilm.get();
      } else {
          UriComponentsBuilder uri = UriComponentsBuilder.fromUriString("https://swapi.dev/api/")
                  .pathSegment("films", String.valueOf(idFilm));
          film = restTemplate.getForObject(uri.toUriString(), Film.class);
          assert film!=null;

      }
          if (!user.getFavoritesFilms().contains(film)) {
              Film savedFilm = repository.saveAndFlush(film);
              user.getFavoritesFilms().add(savedFilm);
              userRepository.save(user);

          }

          return film;
      }


    @Transactional
    public Set<Film> favorite(Long idClient) {
        User user = userService.findUserById(idClient);

        if (user.getFavoritesFilms().isEmpty()) {
            throw new StarLogException("No favorite films found for this user", 404);
        }
        return user.getFavoritesFilms();
    }
}
