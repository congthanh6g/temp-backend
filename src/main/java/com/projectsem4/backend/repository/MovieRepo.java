package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m where m.deleted = false")
    List<Movie> findAllByDeletedIsFalse();
}
