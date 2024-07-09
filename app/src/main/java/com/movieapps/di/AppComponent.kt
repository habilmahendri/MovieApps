package com.movieapps.di

import com.core.di.networkModule
import com.core.di.repositoryModule
import com.core.di.useCaseModule

val appComponent = listOf(
    networkModule, repositoryModule, viewModelModule, useCaseModule
)