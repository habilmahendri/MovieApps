package com.core.domain.usecase

import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import io.reactivex.Observable

interface SearchUseCase {
    fun search(title:String):Observable<BaseResponse<MovieItem>>
}