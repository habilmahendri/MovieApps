package com.core.di

import com.core.model.repository.remote.RemoteDataSource
import com.core.network.ApiInterface
import com.core.utils.Const.NETWORK_TIMEOUT
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZTBmZjMwOGIwMjg2NTJlZmY4YTAzNjM0MmZjY2I1YSIsIm5iZiI6MTcyMDUzMDY5My42NDgyMzMsInN1YiI6IjViMGZkZmQ2OTI1MTQxNWVlOTAwNzJhNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.380nClvrEjb1HjIeom_ZdXrOL0j70t3lgLWI7_5xpAk")
                    .build()
                chain.proceed(modifiedRequest)
            }
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(ApiInterface::class.java)
    }

    single {
        RemoteDataSource(get())
    }
}