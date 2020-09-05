package com.movieservice.controller;

import com.movieservice.data.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
