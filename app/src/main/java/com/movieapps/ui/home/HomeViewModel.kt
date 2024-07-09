package com.movieapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movieapps.base.BaseViewModel
import com.movieapps.utils.NetworkState
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem

class HomeViewModel  constructor(private val useCase: HomeUseCase) : BaseViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _movies = MutableLiveData<BaseResponse<MovieItem>>()
    val movies: LiveData<BaseResponse<MovieItem>>
        get() = _movies


    private val _popular = MutableLiveData<BaseResponse<MovieItem>>()
    val popular: LiveData<BaseResponse<MovieItem>>
        get() = _popular

    private val _video = MutableLiveData<BaseResponse<VideoItem>>()
    val video: LiveData<BaseResponse<VideoItem>>
        get() = _video

    fun getMovies() {
        _networkState.postValue(NetworkState.LOADING)
        useCase.movies().subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _movies.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

    fun getVideos(movie_id: String) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.videos(movie_id).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _video.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

    fun getPopular() {
        _networkState.postValue(NetworkState.LOADING)
        useCase.popular().subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _popular.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

}