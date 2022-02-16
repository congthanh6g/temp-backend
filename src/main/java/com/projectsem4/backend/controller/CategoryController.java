package com.projectsem4.backend.controller;

import com.projectsem4.backend.dto.category.CategoryDto;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.category.CategoryMapper;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.repository.CategoryRepo;
import com.projectsem4.backend.service.CategoryService;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/category/")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    //Lấy list item (chỉ có thể lấy list các item chưa xóa, dành cho user thường)
    @GetMapping("list")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(RESTResponse.success(categoryMapper.INSTANCE
                .lsCategoryToCategoryDtoRes(categoryService
                        .getAllCategories()),"Get list Category successful!")
                , HttpStatus.OK);
    }

    //Lấy list dựa theo trạng thái: đã xóa hay chưa xóa ? (dùng cho admin)
    @GetMapping("admin/list")
    public ResponseEntity<?> getAllCategoriesByDeleteState(@RequestParam(name="isDeleted") boolean deleted){
        return new ResponseEntity<>(RESTResponse.success(categoryMapper.INSTANCE
                .lsCategoryToCategoryDtoRes(categoryService
                        .getAllCategoriesByDeleteState(deleted)),"Get list Category successful!")
                , HttpStatus.OK);
    }

    // Thêm item (nếu ko có trường unique trong entity thì bỏ qua đoạn code check name)
    // Ở đây vì trong entity class Category có trường name với annotation là unique, nên sẽ có thêm đoạn check name,
    // nếu name trùng với name đã có trong database (được tạo trước đó), thì sẽ ko cho insert vào database.
    @PostMapping("add")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        boolean checkName = categoryRepo.existsByName(categoryDto.getName());
        if (checkName){
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Name already exist!")
                    .build(),HttpStatus.BAD_REQUEST);
        }
        Category category = categoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        categoryService.saveCategory(category);
        return new ResponseEntity<>(RESTResponse.success(categoryDto
                ,"Create Category successful!"),HttpStatus.OK);
    }


    // Sửa item theo id
    // Ở đây cũng có đoạn check name, nhưng hàm hơi khác 1 chút, lấy từ service ra, hàm này kiểm tra thêm trường hợp nếu cùng 1 id,
    // chỉ sửa các trường khác mà ko sửa trường name (vì name là unique), thì khi update sẽ ko thực hiện check name nữa (vì name vẫn như cũ).
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(value = "id") int id, @Valid @RequestBody CategoryDto categoryDto){
        try{
            Category category = categoryService.getCategoryById(id);
            boolean checkExistNameUpdate = categoryService.checkExistNameUpdate(categoryDto.getName(),category);
            if (checkExistNameUpdate){
                return new ResponseEntity<>(new RESTResponse.Error()
                        .badRequestWithMessage("Name already exist!")
                        .build(),HttpStatus.BAD_REQUEST);
            }
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            categoryService.saveCategory(category);
            return new ResponseEntity<>(RESTResponse.success(categoryDto
                    ,"Update category successful!"),HttpStatus.OK);
        }catch(EntityNotFoundException e){ }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    // Lấy item theo id
    @GetMapping("get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value="id") int id){
        try{
            Category category = categoryService.getCategoryById(id);
            CategoryDtoRes categoryDtoRes = categoryMapper.INSTANCE.categoryToCategoryDtoRes(category);
            return new ResponseEntity<>(RESTResponse.success(categoryDtoRes
                    ,"Found a category with this id !"),HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }



    //Xóa mềm (tức là ghi đè lệnh xóa trong sql bằng lệnh update)
    //Công việc: dùng hàm delete trong JPA như bình thường, nhưng lúc này query delete của sql đã được đổi thành update,
    // trường thay đổi là trường deleted (trong database).
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> softDeleteCategory(@PathVariable(value="id")int id){
        try{
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }



    //Khôi phục item đã lỡ xóa nhầm
    //Công việc: Lấy ra category theo id truyền vào, sau đó set trường deleted theo param deleted người dùng truyền
    @PutMapping("statechange/{id}")
    public ResponseEntity<?> setDeleteStateCategory(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            Category category = categoryService.getCategoryById(id);
            category.setDeleted(deleted);
            categoryService.saveCategory(category);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }
}
