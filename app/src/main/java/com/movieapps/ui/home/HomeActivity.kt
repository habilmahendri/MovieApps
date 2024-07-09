package com.movieapps.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.core.model.data.MovieItem
import com.movieapps.R
import com.movieapps.ui.home.adapter.ImageSlideAdapter
import com.movieapps.ui.home.adapter.MovieAdapter
import com.movieapps.ui.home.adapter.PopularMovieAdapter
import com.movieapps.utils.NetworkState.Companion.ERROR
import com.movieapps.utils.NetworkState.Companion.LOADED
import com.movieapps.utils.NetworkState.Companion.LOADING
import com.movieapps.utils.PageRoute
import com.movieapps.utils.hide
import com.movieapps.utils.popupDetail
import com.movieapps.utils.show
import com.movieapps.utils.toast
import kotlinx.android.synthetic.main.activity_home.img_search
import kotlinx.android.synthetic.main.activity_home.pg
import kotlinx.android.synthetic.main.activity_home.rvMovie
import kotlinx.android.synthetic.main.activity_home.rvPopular
import kotlinx.android.synthetic.main.activity_home.tv_now_playing
import kotlinx.android.synthetic.main.activity_home.tv_popular
import kotlinx.android.synthetic.main.activity_home.viewpager
import me.relex.circleindicator.CircleIndicator
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()
    lateinit var viewPagerAdapter: ImageSlideAdapter
    lateinit var indicator: CircleIndicator
    private var movieItem = MovieItem(
        originalTitle = "",
        posterPath = "",
        backdrop_path = "",
        release_date = "",
        overview = "",
        id = ""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        observerLiveData()
        initOnClick()
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getMovies()
        viewModel.getPopular()
    }

    private fun observerLiveData() {
        viewModel.movies.observe(this, Observer {
            tv_now_playing.show()
            initSlider(it.results)
            rvMovie.adapter = MovieAdapter(it.results, this) {
                movieItem = it
                viewModel.getVideos(it.id)
            }
        })
        viewModel.video.observe(this, Observer {
            it.results.find { it.type.lowercase() == "trailer" }?.let { teaserItem ->
                popupDetail(
                    context =this@HomeActivity, movieItem = movieItem, key = teaserItem.key
                )
            }
        })
        viewModel.popular.observe(this, Observer {
            tv_popular.show()
            rvPopular.adapter = PopularMovieAdapter(it.results, this) {
                movieItem = it
                viewModel.getVideos(it.id)
            }
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                LOADED -> {
                    pg.hide()
                }

                LOADING -> {
                    pg.show()
                }

                ERROR -> {
                    toast(it.message)
                    pg.hide()
                }
            }
        })
    }

    private fun initSlider(data: List<MovieItem>) {
        viewPagerAdapter = ImageSlideAdapter(this, data.take(5)){
            movieItem = it
            viewModel.getVideos(it.id)
        }
        viewpager.adapter = viewPagerAdapter
        indicator = this.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewpager)
    }

    private fun initOnClick() {
        img_search.setOnClickListener {
            PageRoute.goToSearch(this)
        }
    }

}