package com.core.model.repository

import com.core.domain.repository.AppRepository
import com.core.model.data.BaseResponse
import com.core.model.data.MovieItem
import com.core.model.data.VideoItem
import com.core.model.repository.remote.RemoteDataSource
import io.reactivex.Observable

class AppRepositoryImpl constructor(private val remoteDataSource: RemoteDataSource): AppRepository {

    override fun movies(): Observable<BaseResponse<MovieItem>> {
        val remoteSource = remoteDataSource.movies()
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun popular(): Observable<BaseResponse<MovieItem>> {
        val remoteSource = remoteDataSource.popular()
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun videos(movie_id: String): Observable<BaseResponse<VideoItem>> {
        val remoteSource = remoteDataSource.videos(movie_id)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun search(title: String): Observable<BaseResponse<MovieItem>> {
        val remoteSource = remoteDataSource.search(title)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    data class Result<out T>(
        val data: T? = null,
        val error: Throwable? = null
    )

    private fun <T> T.asResult(): Result<T> = Result(data = this, error = null)

}