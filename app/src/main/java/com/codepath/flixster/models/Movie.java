package com.codepath.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    int movieId;
    String releaseDate;
    String popularity;

    //empty constructor needed by the Parceler library
    public Movie() { }

    //.getString() might throw an exception if cannot find name that matches parameter;
    //since throws is part of method signature, caller of this method is then responsible
    //for handling the exception
    public Movie(JSONObject jsonObject) throws JSONException {
        //.getString() returns the String mapped by the name given as a parameter
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
        releaseDate = jsonObject.getString("release_date");
        popularity = jsonObject.getString("popularity");
    }

    //iterates through the JSONArray and constructs a Movie object for each element in the array,
    //then returns a list of those Movie objects
    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++) {
            //gets the JSONObject at index i, creates it as a new Movie object, and adds it to
            //the list of movies
            movies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackdropPath() {
        //appends unique backdropPath directory to base URL and hardcoded image size
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {
        //appends unique posterPath directory to base URL and hardcoded image size
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
         return rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }
}
