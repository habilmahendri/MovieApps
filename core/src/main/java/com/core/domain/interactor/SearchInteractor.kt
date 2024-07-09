package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.HomeUseCase
import com.core.domain.usecase.SearchUseCase
import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import io.reactivex.Observable

class SearchInteractor(private val appRepository: AppRepository): SearchUseCase {
    override fun search(title: String): Observable<BaseResponse<MovieItem>> = appRepository.search(title)
}