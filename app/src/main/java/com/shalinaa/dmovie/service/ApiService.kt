package com.shalinaa.dmovie.service

import com.shalinaa.dmovie.model.ResponseMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovieData(
        @Query("api_key") apiKey : String?

    ): Call<ResponseMovie>
}