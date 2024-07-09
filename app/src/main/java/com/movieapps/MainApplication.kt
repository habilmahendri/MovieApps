package com.movieapps

import android.app.Application
import com.movieapps.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appComponent)
        }
    }
}