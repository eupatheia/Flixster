package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.flixster.DetailActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning it inside a ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        //inflates item_movie.xml
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        //returns a new ViewHolder containing the inflated layout
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //get the movie at the passed position
        Movie movie = movies.get(position);
        //bind the movie data into the ViewHolder
        holder.bind(movie);
    }

    //Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //ViewHolder object describes and provides access to all the views within each item row
    public class ViewHolder extends RecyclerView.ViewHolder {
        //member variables for views set as row is rendered
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        //an entire recycler view row is passed in as a View to this constructor
        //which then uses view lookups to find each subview
        public ViewHolder(@NonNull View itemView) {
            //stores the itemView in a public final member variable that can be used
            //to access the context from any ViewHolder instance
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            //sets member variables by calling getters in the specific Movie
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if phone is in landscape, use backdrop image; else use poster image
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            //loads the image from the Movie's posterPath into the ImageView
            Glide.with(context).load(imageUrl).into(ivPoster);

            //register the onClickListener on the entire row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    //passes an entire Movie object to DetailActivity
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
