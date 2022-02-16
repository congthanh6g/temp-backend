package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getAllMoviesByDeleteState(boolean isDeleted);

    Movie saveMovie(Movie movie);
    Movie getMovieById(int id);
    void deleteMovieById(int id);
}
