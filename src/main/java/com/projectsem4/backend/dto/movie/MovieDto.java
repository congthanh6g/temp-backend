package com.projectsem4.backend.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    @NotBlank(message = "Name must not be blank!")
    private String name;
    private String description;
    private int duration;
    private String movieType;
    private int categoryId;
    private int directorId;
}
