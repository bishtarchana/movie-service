package com.movieservice.data;

public class Movie {
    private String name;
    private int id;
    private  int releaseYear;

    public Movie(String name,int id,int releaseYear){
        this.name = name;
        this.id = id;
        this.releaseYear = releaseYear;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
    public int getReleaseYear(){
        return releaseYear;
    }

    @Override
    public java.lang.String toString() {
        return id + " " + name + " " + releaseYear;
    }
}
