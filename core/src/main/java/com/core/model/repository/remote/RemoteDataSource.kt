package com.core.model.repository.remote

import com.core.network.ApiInterface

class RemoteDataSource constructor(private val apiService: ApiInterface)  {
    fun movies() = apiService.movies()
    fun popular() = apiService.popular()
    fun videos(movie_id:String) =  apiService.videos(movie_id)
    fun search(title:String) = apiService.search(title)
}