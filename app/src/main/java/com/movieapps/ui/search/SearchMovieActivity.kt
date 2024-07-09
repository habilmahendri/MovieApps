package com.movieapps.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.core.model.data.MovieItem
import com.movieapps.R
import com.movieapps.ui.home.HomeViewModel
import com.movieapps.ui.search.adapter.SearchMovieAdapter
import com.movieapps.utils.NetworkState
import com.movieapps.utils.afterTextChanged
import com.movieapps.utils.hide
import com.movieapps.utils.popupDetail
import com.movieapps.utils.show
import com.movieapps.utils.toast
import kotlinx.android.synthetic.main.activity_search_movie.edt_search
import kotlinx.android.synthetic.main.activity_search_movie.pg
import kotlinx.android.synthetic.main.activity_search_movie.rv_search_movie
import kotlinx.android.synthetic.main.activity_search_movie.tv_desc
import org.koin.android.viewmodel.ext.android.viewModel

class SearchMovieActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModel()
    private val viewModelHome: HomeViewModel by viewModel()
    private var movieItem = MovieItem(
        originalTitle = "",
        posterPath = "",
        backdrop_path = "",
        release_date = "",
        overview = "",
        id = ""
    )

    companion object {
        fun startThisActivity(activity: Context) {
            activity.startActivity(
                Intent(activity, SearchMovieActivity::class.java)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        initListener()
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.search.observe(this, Observer {
            if (it.results.isNotEmpty()) {
                tv_desc.hide()
            } else {
                tv_desc.show()
                tv_desc.text = "Film Yang Kamu Cari Tidak Ditemukan"
            }
            rv_search_movie.adapter = SearchMovieAdapter(it.results, this) {
                movieItem = it
                viewModelHome.getVideos(it.id)
            }
        })

        viewModelHome.video.observe(this, Observer {
            it.results.find { it.type.lowercase() == "trailer" }?.let { teaserItem ->
                popupDetail(
                    context =this@SearchMovieActivity, movieItem = movieItem, key = teaserItem.key
                )
            }
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                NetworkState.LOADED -> {
                    pg.hide()
                }

                NetworkState.LOADING -> {
                    pg.show()
                }

                NetworkState.ERROR -> {
                    toast(it.message)
                    pg.hide()
                }
            }
        })
    }

    private fun initListener() {
        edt_search.afterTextChanged {
            viewModel.getSearch(it)
        }
    }

}