package com.projectsem4.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerMovieKey implements Serializable {
    @Column(name = "customer_id")
    public int customerId;

    @Column(name = "movie_id")
    public int movieId;
}
