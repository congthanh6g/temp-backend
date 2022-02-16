package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Table(name = "movies")
public class Movie extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String thumbnail;
    private String description;
    private int duration;
    private int view;


    @Column(name = "movie_type")
    private String movieType;

    private String url;
    private String images;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_categories" ,
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_casts" ,
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> casts = new HashSet<Cast>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<Director>();


    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;
}
