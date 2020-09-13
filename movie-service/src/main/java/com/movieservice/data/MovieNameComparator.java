package com.movieservice.data;

import java.util.Comparator;

public class MovieNameComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie obj1, Movie obj2){
        return obj1.getName().compareTo(obj2.getName());
    }
}
