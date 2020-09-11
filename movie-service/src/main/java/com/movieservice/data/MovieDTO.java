package com.movieservice.data;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {


    private String name;
    private int releaseYear;

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getName(){
        return name;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public static MovieDTO prepareMovieDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName(movie.getName());
        movieDTO.setReleaseYear(movie.getReleaseYear());
        return movieDTO;
    }
}
