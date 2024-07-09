package com.core.network

import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    @GET("3/movie/now_playing")
    fun movies(
    ): Observable<BaseResponse<MovieItem>>

    @GET("3/movie/popular")
    fun popular(
    ): Observable<BaseResponse<MovieItem>>

    @GET("3/movie/{movie_id}/videos")
    fun videos(
        @Path("movie_id") movie_id: String
    ): Observable<BaseResponse<VideoItem>>

    @GET("3/search/movie")
    fun search(
        @Query("query") search: String
    ): Observable<BaseResponse<MovieItem>>
}