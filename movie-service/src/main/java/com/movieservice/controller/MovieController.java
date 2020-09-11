package com.movieservice.controller;

import com.movieservice.data.Movie;
import com.movieservice.data.MovieDTO;
import com.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/home")
    public String displayHome(){
        return "welcome" ;
    }

    @GetMapping("/movie")
    @ResponseBody()
    public List<Movie> getMovieDetail(){
        return getAllMovieDetail();
    }

    public List<Movie> getAllMovieDetail(){
        Movie movie = new Movie("Rang de basanti", 1, 2008);
        Movie movie1 = new Movie("DB", 2, 2011);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie1);
        return movies;
    }

    @RequestMapping(value="/addMovie", method= RequestMethod.POST,headers="Accept=application/json",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie newMovie){
        MovieDTO movieDTO = movieService.saveMovie(newMovie);
        return new ResponseEntity<MovieDTO>(movieDTO,HttpStatus.OK);

    }

    @GetMapping("/displayMovieForm")
    public String displayMovieForm(@ModelAttribute("movieForm")Movie movie){
        System.out.println("Displaying movie form");
        return "addMovie";
    }

    @RequestMapping(value="/addMovieFromView", method = RequestMethod.POST)
    public ResponseEntity<MovieDTO> addMovieFromView(@ModelAttribute("movieForm")Movie movie){
        MovieDTO movieDTO = movieService.saveMovieFromView(movie);
        return new ResponseEntity<MovieDTO>(movieDTO,HttpStatus.OK);
    }

}
