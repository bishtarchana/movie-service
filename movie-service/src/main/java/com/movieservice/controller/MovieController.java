package com.movieservice.controller;

import com.movieservice.data.Movie;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

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
    public ResponseEntity<String> addMovie(@RequestBody Movie newMovie){
        return new ResponseEntity<>("New movie added" + newMovie.getName(),HttpStatus.OK);

    }
}
