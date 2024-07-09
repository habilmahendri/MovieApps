package com.movieapps.di

import com.movieapps.ui.home.HomeViewModel
import com.movieapps.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}