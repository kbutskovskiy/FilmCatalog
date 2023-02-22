package com.example.catalogfilm.service;

import com.example.catalogfilm.model.Film;

import java.util.UUID;

public interface FilmService {
    Film getFilm(UUID filmUuid);
    Film saveFilm(Film film);
}
