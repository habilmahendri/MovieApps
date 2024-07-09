package com.core.domain.usecase

import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import io.reactivex.Observable

interface HomeUseCase {
    fun movies():Observable<BaseResponse<MovieItem>>
    fun popular():Observable<BaseResponse<MovieItem>>
    fun videos(movie_id:String):Observable<BaseResponse<VideoItem>>
}