package com.projectsem4.backend.dto.movie;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDtoRes {
    private int id;
    private String name;
    private String thumbnail;
    private String description;
    private int duration;
    private int view;
    private int likeCount;
    private String movieType;
    private String url;
    private String images;
    private int categoryId;
    private int directorId;
    private Set<Cast> casts;
    private List<Comment> comments;
}
