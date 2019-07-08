package com.dngwjy.filmapp.data

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by wijaya on 05/07/19
 */
interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc&api_key=75afbeac6103b3207a6e977639450db4&page=1")
    fun getMovie():Observable<MovieResponse>
}