package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.repository.CategoryRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkExistNameUpdate(String name, Category category) {
        try{
            boolean bl = categoryRepo.existsByName(name);
            if (bl){
                if (!category.getName().equals(name)){
                    return true;
                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Category> getAllCategoriesByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Category> categories = categoryRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.getById(id);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.categoryRepo.deleteById(id);
    }
}
