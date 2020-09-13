package com.movieservice.controller;

import com.movieservice.data.Movie;
import com.movieservice.data.MovieDTO;
import com.movieservice.data.MovieNameComparator;
import com.movieservice.data.MovieReleaseComparator;
import com.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        return movieService.getAllMovies();
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

    @GetMapping("/movieWithinRangeOfYear")
    @ResponseBody()
    public List<Movie> getMovieWithinRangeOfYear(){
        List<Movie> allMovie = movieService.getAllMovies();
        List<Movie> filteredListMovie = allMovie.parallelStream().filter(i -> i.getReleaseYear() >= 2000 && i.getReleaseYear() <= 2005).collect(Collectors.toList());
        Collections.sort(filteredListMovie);
        return filteredListMovie;
    }

    @GetMapping("/movieByYearAsc")
    @ResponseBody()
    public List<Movie> getMoviesByYearAsc(){
        List<Movie> movieListInAscOrder = getAllMovieDetail();
        Collections.sort(movieListInAscOrder);
        return movieListInAscOrder;
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

    public List<Movie> getAllMovieDetail(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Rang de basanti", 1, 2008));
        movies.add(new Movie("Second movie", 2, 2004));
        movies.add(new Movie("Third movie", 3, 2005));
        movies.add(new Movie("Fourth movie", 4, 2004));
        movies.add(new Movie("Fifth movie", 5, 2006));
        movies.add(new Movie("Sixth movie", 6, 1998));
        movies.add(new Movie("Seventh movie", 7, 1996));
        movies.add(new Movie("Eighth movie", 8, 1991));
        movies.add(new Movie("Nineth movie", 9, 1996));
        movies.add(new Movie("Tenth movie", 10, 1789));
        return movies;
    }

    @GetMapping("/getMovieByRange")
    @ResponseBody()
    public List<Movie> getMovieByRange(){
        List<Movie> movies = getAllMovieDetail();

        List<Movie> filteredMovies = movies.stream().filter(i -> i.getReleaseYear() >= 2000 && i.getReleaseYear() <= 2005).collect(Collectors.toList());
        return filteredMovies;
    }

    @GetMapping("/getEvenNumber")
    @ResponseBody
    public List<Integer> getEvenNumber(){
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(16);
        num.add(178);
        num.add(134);
        num.add(120);
        num.add(186);
        num.add(1215);
        num.add(146);
        num.add(3571);
        num.add(871);

        List<Integer> evenNumbers = num.parallelStream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        return evenNumbers;
    }


    @RequestMapping(value="/addMovie", method= RequestMethod.POST,headers="Accept=application/json",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie newMovie){
        MovieDTO movieDTO = movieService.saveMovie(newMovie);
        return new ResponseEntity<MovieDTO>(movieDTO,HttpStatus.OK);

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

}
