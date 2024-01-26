package de.michiruf.moviedb.repository;

import de.michiruf.moviedb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
