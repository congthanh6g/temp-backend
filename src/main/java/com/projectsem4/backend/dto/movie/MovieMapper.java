package com.projectsem4.backend.dto.movie;

import com.projectsem4.backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    //MovieDto mappers
    Movie movieDtoToMovie(MovieDto movieDto);
    MovieDto movieToMovieDto(Movie movie);

    //MovieDtoRes mappers
    Movie movieDtoResToMovie(MovieDtoRes movieDtoRes);
    MovieDtoRes movieToMovieDtoRes(Movie movie);
    List<MovieDtoRes> lsMovieToMovieDtoRes(List<Movie> movies);
}
