package com.movieservice.service;

import com.movieservice.data.Movie;
import com.movieservice.data.MovieDTO;
import com.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieDTO saveMovie(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        System.out.println("Data has been saved");
        return MovieDTO.prepareMovieDTO(savedMovie);
    }

    public MovieDTO saveMovieFromView(Movie movie){
        Movie savedMovieFromView = movieRepository.save(movie);
        return MovieDTO.prepareMovieDTO(savedMovieFromView);
    }

    public List<Movie> getAllMovies(){
        List<Movie> movieList = (List<Movie>) movieRepository.findAll();
        return movieList;
    }

    public void deleteMovie(Integer id){
        movieRepository.deleteById(id);
    }

}
