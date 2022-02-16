package com.projectsem4.backend.controller;

import com.projectsem4.backend.dto.director.DirectorDto;
import com.projectsem4.backend.dto.director.DirectorDtoRes;
import com.projectsem4.backend.dto.director.DirectorMapper;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.service.DirectorService;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/director/")
public class DirectorController {

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private DirectorService directorService;

    @GetMapping("list")
    public ResponseEntity<?> getAllDirectors(){
        return new ResponseEntity<>(RESTResponse.success(directorMapper.INSTANCE
                .lsDirectorToDirectorDtoRes(directorService
                        .getAllDirectors()),"Get list Movie successful!")
                , HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addDirector(@Valid @RequestBody DirectorDto directorDto) {
        Director director = directorMapper.INSTANCE.directorDtoToDirector(directorDto);
        directorService.saveDirector(director);
        return new ResponseEntity<>(RESTResponse.success(directorDto
                ,"Create Movie successful!"),HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable(value="id") int id){
        try{
            Director director = directorService.getDirectorById(id);
            DirectorDtoRes directorDtoRes = directorMapper.INSTANCE.directorToDirectorDtoRes(director);
            return new ResponseEntity<>(RESTResponse.success(directorDtoRes
                    ,"Found a movie with this id !"),HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }
}
