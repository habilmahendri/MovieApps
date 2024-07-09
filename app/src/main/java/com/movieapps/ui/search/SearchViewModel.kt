package com.movieapps.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movieapps.base.BaseViewModel
import com.movieapps.utils.NetworkState
import com.core.domain.usecase.HomeUseCase
import com.core.domain.usecase.SearchUseCase
import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem

class SearchViewModel  constructor(private val useCase: SearchUseCase) : BaseViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _search = MutableLiveData<BaseResponse<MovieItem>>()
    val search: LiveData<BaseResponse<MovieItem>>
        get() = _search


    fun getSearch(title:String) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.search(title).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _search.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

}