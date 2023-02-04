package com.example.mobileprogramminguas;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mobileprogramminguas.Helper.IMDBInterface;
import com.example.mobileprogramminguas.Helper.RecyclerViewInterface;
import com.example.mobileprogramminguas.Helper.RetrofitHelper;
import com.example.mobileprogramminguas.Helper.TheAdapterMovies;
import com.example.mobileprogramminguas.Models.ApiResponse;
import com.example.mobileprogramminguas.Models.Movie;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements RecyclerViewInterface {

    TheAdapterMovies myAdapter;
    RecyclerView recyclerView;
    List<Movie> movies = new ArrayList<Movie>();

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching API...");
        progressDialog.show();

        IMDBInterface imdbInterface = RetrofitHelper.getRetrofitInstance().create(IMDBInterface.class);
        Call<ApiResponse> call = imdbInterface.getAllMovies();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                retrieveMovies(response.body().getItems(), view);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Fetch failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        return view;
    }

    public void retrieveMovies(List<Movie> movies , View view){
        this.movies = movies;

        recyclerView = view.findViewById(R.id.movieContainer);
        myAdapter =  new TheAdapterMovies(this,getActivity().getApplicationContext(),movies);
        progressDialog.dismiss();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("title", movies.get(position).getFullTitle());
        bundle.putString("rating", movies.get(position).getContentRating());
        bundle.putString("genres", movies.get(position).getGenres());
        bundle.putString("image", movies.get(position).getImage());
        bundle.putString("plot", movies.get(position).getPlot());
        bundle.putString("duration", movies.get(position).getRuntimeStr());
        bundle.putString("date", movies.get(position).getReleaseState());

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.changeToMoviePage(bundle);
    }

}