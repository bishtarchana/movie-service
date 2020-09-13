package com.movieservice.data;

import java.util.function.Predicate;

public class MoviePredicate {

    public static Predicate<Movie> getMovieBySpecificRange(int firstYear, int secondYear){
        return movie ->  movie.getReleaseYear() >=firstYear && movie.getReleaseYear() <=secondYear ;
    }

    public static Predicate<Integer> getEvenNummber(){
        return num -> num % 2 == 0 ;
    }

}
