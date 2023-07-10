package com.freecast.thatmovieapp.home.data.service

import com.freecast.thatmovieapp.home.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): GetMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") id: Int?
    ): MovieDetailsResponse

    @GET("genre/tv/list")
    suspend fun getSeriesGenres(): GetGenreResponse

    @GET("discover/tv")
    suspend fun getSeriesByGenre(
        @Query("with_genres") id: Int?,
        @Query("page") page: Int
    ): GetSeriesResponse

    @GET("tv/{series_id}")
    suspend fun getDetailsSeries(
        @Path("series_id") id: Int?
    ): SeriesDetailResponse

}