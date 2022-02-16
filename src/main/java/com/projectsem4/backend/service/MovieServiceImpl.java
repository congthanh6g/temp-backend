package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Movie;
import com.projectsem4.backend.repository.MovieRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Movie> getAllMoviesByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Movie> movies = movieRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return movies;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepo.getById(id);
    }

    @Override
    public void deleteMovieById(int id) {
        this.movieRepo.deleteById(id);
    }
}
