package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_movie_favorite")
public class CustomerMovieFavorite extends BaseEntity{
    @EmbeddedId
    private CustomerMovieKey id;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customerId;

    @Column(name = "movie_id", insertable = false, updatable = false)
    private int movieId;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "favorite_id")
    private int favoriteId;

    @ManyToOne
    @JoinColumn(name = "favorite_id", insertable = false, updatable = false)
    private Favorite favorite;
}
