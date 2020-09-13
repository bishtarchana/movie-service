package com.movieservice.data;

import javax.persistence.*;

@Entity
@Table(name="MOVIE")
public class Movie implements Comparable<Movie> {

    public Movie(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MOVIE_ID")
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="RELEASE_YEAR")
    private  Integer releaseYear;

    public Movie(String name,Integer id,Integer releaseYear){
        this.name = name;
        this.id = id;
        this.releaseYear = releaseYear;
    }

    public String getName(){
        return name;
    }

    public Integer getId(){
        return id;
    }
    public Integer getReleaseYear(){
        return releaseYear;
    }

    @Override
    public java.lang.String toString() {
        return id + " " + name + " " + releaseYear;
    }

    @Override
    public int compareTo(Movie movie){
        return this.releaseYear.compareTo(movie.releaseYear);
    }
}
