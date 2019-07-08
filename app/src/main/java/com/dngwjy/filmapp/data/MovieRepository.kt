package com.dngwjy.filmapp.data

import com.dngwjy.filmapp.presentation.model.MovieModel
import io.reactivex.Observable

/**
 * Created by wijaya on 05/07/19
 */
class MovieRepository {
    fun getData():Observable<List<MovieModel>>{
        return ApiClient.create().getMovie()
            .flatMapIterable {
                it.results.map {
                    MovieModel(
                        adult = it.adult,
                        backdropPath = it.backdropPath,
                        genreIds = it.genreIds,
                        id = it.id,
                        originalTitle = it.originalTitle,
                        originalLanguage = it.originalLanguage,
                        overview = it.overview,
                        popularity = it.popularity,
                        posterPath = it.posterPath,
                        releaseDate = it.releaseDate,
                        title = it.title,
                        video = it.video,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount
                    )
                }}.toList().toObservable()
    }
}