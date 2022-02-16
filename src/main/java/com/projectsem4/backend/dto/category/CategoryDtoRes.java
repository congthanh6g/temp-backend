package com.projectsem4.backend.dto.category;

import com.projectsem4.backend.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoRes extends BaseDto {
    private int id;
    private String name;
    private String description;
}
