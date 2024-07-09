package com.core.di

import com.core.domain.repository.AppRepository
import com.core.model.repository.AppRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module{
    factory<AppRepository> {
        AppRepositoryImpl(get())
    }
}
