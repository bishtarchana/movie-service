package com.movieservice.controller;

import com.movieservice.data.*;
import com.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoviePostmanController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movie")
    @ResponseBody()
    public List<Movie> getMovieDetail(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movieWithinRangeOfYear")
    @ResponseBody()
    public List<Movie> getMovieWithinRangeOfYear(){
        List<Movie> allMovie = movieService.getAllMovies();
        List<Movie> filteredListMovie = movieService.getMoviesWithinSpecificRange(1991,2002, allMovie);
        Collections.sort(filteredListMovie);
        return filteredListMovie;
    }

    @GetMapping("/movieByReleaseYearComparator")
    @ResponseBody()
    public List<Movie> getMovieByReleaseYearComparator(){
        List<Movie> movieByReleaseYearComparator = movieService.getAllMovies();
        Collections.sort(movieByReleaseYearComparator, new MovieReleaseComparator());
        return movieByReleaseYearComparator;
    }

    @GetMapping("/movieByNameComparator")
    @ResponseBody()
    public List<Movie> getMovieNameComparator(){
        List<Movie> movieByNameComparator = movieService.getAllMovies();
        Collections.sort(movieByNameComparator, new MovieNameComparator());
        return movieByNameComparator;
    }

    @GetMapping("/getMovieByRange")
    @ResponseBody()
    public List<Movie> getMovieByRange(){
        List<Movie> movies = movieService.getAllMovies();
        List<Movie> filteredMovies = movieService.getMoviesWithinSpecificRange(2000,2005, movies);
        return filteredMovies;
    }

    @RequestMapping(value="/addMovie", method= RequestMethod.POST,headers="Accept=application/json",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie newMovie){
        MovieDTO movieDTO = movieService.saveMovie(newMovie);
        return new ResponseEntity<MovieDTO>(movieDTO, HttpStatus.OK);

    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMovie(){
        movieService.deleteMovie(4);
        return "Movie deleted";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST,headers="Accept=application/json",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> updateMovie(@RequestBody Movie updateMovie){
        MovieDTO movieDTO = movieService.saveMovie(updateMovie);
        return new ResponseEntity<MovieDTO>(movieDTO,HttpStatus.OK);
    }

    @GetMapping("/getMovieNameInUpperCase")
    @ResponseBody()
    public List<String> getMovieNameInUpperCase(){
        List<String> newMovieList = new ArrayList<>();
        List<Movie> movies = movieService.getAllMovies();

        /**
         * Following are the different ways of iterating through List
         */

        /**
         * THROUGH ITERATOR
         * ListIterator<Movie> iterator = movies.listIterator();
         *        while (iterator.hasNext())
         *         {
         *             String s = iterator.next().getName().toUpperCase();
         *               newMovieList.add(s);
         *         }
         */

        /**
         * THROUGH FOR EACH
         * for(Movie movie : movies){
         *    newMovieList.add(movie.getName().toUpperCase());
         * }
         */

        /**
         * THROUGH PARALLELSTREAM
         */
        List<String> nameInUpperCase = movieService.getMovieNameInUpperCase(movies);
        return nameInUpperCase;
    }

}
