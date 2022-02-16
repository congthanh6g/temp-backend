package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectorRepo extends JpaRepository<Director,Integer> {
    @Query("select d from Director d where d.deleted = false")
    List<Director> findAllByDeletedIsFalse();
}
