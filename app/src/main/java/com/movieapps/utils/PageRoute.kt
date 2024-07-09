package com.movieapps.utils

import android.content.Context
import com.movieapps.ui.search.SearchMovieActivity

object PageRoute {

    fun goToSearch(context: Context) {
        SearchMovieActivity.startThisActivity(context)
    }
}