package com.movieapps.utils

class NetworkState(val message: String) {

    companion object {
        val LOADED: NetworkState = NetworkState("Success")
        val LOADING: NetworkState = NetworkState("Running")
        val ERROR: NetworkState = NetworkState("Something went wrong")
    }

}