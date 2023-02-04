package com.example.mobileprogramminguas.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogramminguas.Models.Movie;
import com.example.mobileprogramminguas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TheAdapterMovies extends RecyclerView.Adapter<TheViewHolderMovies> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Movie> movies;

    public TheAdapterMovies(RecyclerViewInterface recyclerViewInterface, Context context, List<Movie> movies) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public TheViewHolderMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item,parent,false);
        return new TheViewHolderMovies(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolderMovies holder, int position) {
        Picasso.get().load(movies.get(position).getImage()).resize(1700, 2500).onlyScaleDown().into(holder.movieImage);
        holder.title.setText("Title: "+movies.get(position).getFullTitle());
        holder.length.setText("Duration: "+movies.get(position).getRuntimeStr());
        holder.genre.setText("Genres: "+movies.get(position).getGenres());
        holder.date.setText("Release Date: "+movies.get(position).getReleaseState());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
