package com.movieservice.controller;

import com.movieservice.data.*;
import com.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/home")
    public String displayHome(){
        return "welcome" ;
    }

    @GetMapping("/displayMovieForm")
    public String displayMovieForm(@ModelAttribute("movieForm")Movie movie){
        System.out.println("Displaying movie form");
        return "addMovie";
    }

    @RequestMapping(value="/addMovieFromView", method = RequestMethod.POST)
    public ResponseEntity<MovieDTO> addMovieFromView(@ModelAttribute("movieForm")Movie movie){
        System.out.println(movie.getName() + movie.getReleaseYear());
        MovieDTO movieDTO = movieService.saveMovieFromView(movie);
        return new ResponseEntity<MovieDTO>(movieDTO,HttpStatus.OK);
    }
}
