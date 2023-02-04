package com.example.mobileprogramminguas.Helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogramminguas.R;

public class TheViewHolderMovies extends RecyclerView.ViewHolder {

    ImageView movieImage;
    TextView title,length,genre,date;


    public TheViewHolderMovies(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);

        movieImage = itemView.findViewById(R.id.movieImage);
        title = itemView.findViewById(R.id.movieTitle);
        length = itemView.findViewById(R.id.movieLength);
        genre = itemView.findViewById(R.id.movieGenres);
        date = itemView.findViewById(R.id.movieDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewInterface != null){
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
