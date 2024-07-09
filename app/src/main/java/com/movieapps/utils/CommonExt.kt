package com.movieapps.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.core.model.data.MovieItem
import com.movieapps.R

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invis(){
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.loadPoster(url: String) {
    Glide.with(this)
        .load("http://image.tmdb.org/t/p/w185$url")
        .into(this)
}
fun ImageView.loadBackdrop(url: String) {
    Glide.with(this)
        .load("https://image.tmdb.org/t/p/original$url")
        .into(this)
}

fun WebView.loadVideoWebView(key:String) {
    this.setWebViewClient(object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }
    })

    val webSettings: WebSettings = this.getSettings()
    webSettings.javaScriptEnabled = true
    webSettings.loadWithOverviewMode = true
    webSettings.useWideViewPort = true

    this.loadUrl("https://www.youtube.com/embed/$key")
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun popupDetail(context: Context, movieItem: MovieItem, key: String) {
    val customDialog = AlertDialog.Builder(context)
    val v = LayoutInflater.from(context).inflate(R.layout.layout_detail_modal, null)
    customDialog.setView(v)

    val dialog = customDialog.create()
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.show()

    val youtubeWebView = v.findViewById<WebView>(R.id.youtube_web_view)
    val close = v.findViewById<ImageView>(R.id.img_close)
    val overview = v.findViewById<TextView>(R.id.tv_overview)
    val title = v.findViewById<TextView>(R.id.tv_title)
    title.text = movieItem.originalTitle
    overview.text = movieItem.overview
    close.setOnClickListener {
        dialog.dismiss()
    }
    youtubeWebView.loadVideoWebView(key)
}
