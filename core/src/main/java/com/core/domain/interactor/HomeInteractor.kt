package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import io.reactivex.Observable

class HomeInteractor(private val appRepository: AppRepository): HomeUseCase {
    override fun movies(): Observable<BaseResponse<MovieItem>> = appRepository.movies()
    override fun popular(): Observable<BaseResponse<MovieItem>> = appRepository.popular()
    override fun videos(movie_id: String): Observable<BaseResponse<VideoItem>> = appRepository.videos(movie_id)
}