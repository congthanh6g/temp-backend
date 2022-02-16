package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.repository.DirectorRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public List<Director> getAllDirectors() {
        return directorRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Director> getAllDirectorsByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Director> directors = directorRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return directors;
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepo.save(director);
    }

    @Override
    public Director getDirectorById(int id) {
        return directorRepo.getById(id);
    }

    @Override
    public void deleteDirectorById(int id) {
        this.directorRepo.deleteById(id);
    }
}
