package de.michiruf.moviedb.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    public Long id;

    public String title;

    public Long getId() {
        return id;
    }
}
