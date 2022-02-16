package com.projectsem4.backend.service;


import com.projectsem4.backend.entity.Director;

import java.util.List;

public interface DirectorService {
    List<Director> getAllDirectors();
    List<Director> getAllDirectorsByDeleteState(boolean isDeleted);

    Director saveDirector(Director director);
    Director getDirectorById(int id);
    void deleteDirectorById(int id);
}
