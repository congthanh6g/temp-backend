package com.projectsem4.backend.dto.director;

import com.projectsem4.backend.dto.category.CategoryDto;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.category.CategoryMapper;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    //DirectorDto mappers
    Director directorDtoToDirector(DirectorDto directorDto);
    DirectorDto directorToDirectorDto(Director director);

    //DirectorDtoRes mappers
    Director directorDtoResToDirector(DirectorDto directorDto);
    DirectorDtoRes directorToDirectorDtoRes(Director director);
    List<DirectorDtoRes> lsDirectorToDirectorDtoRes(List<Director> directors);
}
