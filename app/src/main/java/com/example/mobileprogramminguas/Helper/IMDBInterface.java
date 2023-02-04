package com.example.mobileprogramminguas.Helper;

import com.example.mobileprogramminguas.Models.ApiResponse;
import com.example.mobileprogramminguas.Models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IMDBInterface {

    @GET("InTheaters/k_icsch6uh")
    Call<ApiResponse> getAllMovies();

}
