package com.movieapps.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.model.data.MovieItem
import com.movieapps.R
import com.movieapps.utils.loadPoster
import kotlinx.android.synthetic.main.item_view_popular.view.container
import kotlinx.android.synthetic.main.item_view_popular.view.img_view
import kotlinx.android.synthetic.main.item_view_popular.view.tv_title


class PopularMovieAdapter (private val data: List<MovieItem>, private val context: Context?, private val onClick:(MovieItem)->Unit) :
    RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_view_popular, parent, false
            )
        )

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position],onClick)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieItem,onClick:(MovieItem)->Unit) {

            with(itemView) {
                tv_title.text = data.originalTitle
                img_view.loadPoster(data.posterPath.orEmpty())

                container.setOnClickListener {
                    onClick(data)
                }

            }


        }
    }

}