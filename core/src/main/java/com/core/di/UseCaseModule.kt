package com.core.di

import com.core.domain.interactor.HomeInteractor
import com.core.domain.interactor.SearchInteractor
import com.core.domain.usecase.HomeUseCase
import com.core.domain.usecase.SearchUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<HomeUseCase> {
        HomeInteractor(get())
    }
    factory<SearchUseCase> {
        SearchInteractor(get())
    }
}