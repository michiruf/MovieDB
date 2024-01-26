package de.michiruf.moviedb.controller;

import de.michiruf.moviedb.entity.Movie;
import de.michiruf.moviedb.exception.MovieNotFoundException;
import de.michiruf.moviedb.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieRepository repository;

    private MovieController(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    @GetMapping("/movie")
    private List<Movie> getAll() {
        return repository.findAll();
    }

    @PostMapping("/movie")
    private Movie postNew(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @GetMapping("/movie/{id}")
    private Movie getOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/movie/{id}")
    private Movie replaceOne(@RequestBody Movie movie, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.title = movie.title;
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    movie.id = id;
                    return repository.save(movie);
                });
    }


    @DeleteMapping("/movie/{id}")
    private void deleteOne(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
