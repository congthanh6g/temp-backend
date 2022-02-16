package com.projectsem4.backend.controller;

import com.projectsem4.backend.dto.movie.MovieDto;
import com.projectsem4.backend.dto.movie.MovieDtoRes;
import com.projectsem4.backend.dto.movie.MovieMapper;
import com.projectsem4.backend.entity.Movie;
import com.projectsem4.backend.service.MovieService;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/movie/")
public class MovieController {
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    //Normal list
    @GetMapping("list")
    public ResponseEntity<?> getAllMovies(){
        return new ResponseEntity<>(RESTResponse.success(movieMapper.INSTANCE
                .lsMovieToMovieDtoRes(movieService
                        .getAllMovies()),"Get list Movie successful!")
                , HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDto movieDto){
        Movie movie = movieMapper.INSTANCE.movieDtoToMovie(movieDto);
        movieService.saveMovie(movie);
        return new ResponseEntity<>(RESTResponse.success(movieDto
                ,"Create Movie successful!"),HttpStatus.OK);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable(value="id") int id){
        try{
            Movie movie = movieService.getMovieById(id);
            MovieDtoRes movieDtoRes = movieMapper.INSTANCE.movieToMovieDtoRes(movie);
            return new ResponseEntity<>(RESTResponse.success(movieDtoRes
                    ,"Found a movie with this id !"),HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }
}
