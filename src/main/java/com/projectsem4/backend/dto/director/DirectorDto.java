package com.projectsem4.backend.dto.director;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    @NotBlank(message = "Name must not be blank!")
    private String name;
    private String avt;
    private String description;
}
