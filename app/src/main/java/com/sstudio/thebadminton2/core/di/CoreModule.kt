package com.sstudio.thebadminton2.core.di

import com.sstudio.thebadminton2.BuildConfig
import com.sstudio.thebadminton2.core.data.TheBadmintonRepository
import com.sstudio.thebadminton2.core.data.remote.RemoteDataSource
import com.sstudio.thebadminton2.core.data.remote.api.ApiService
import com.sstudio.thebadminton2.core.domain.repository.ITheBadmintonRepository
import com.sstudio.thebadminton2.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITheBadmintonRepository> { TheBadmintonRepository(get(), get()) }
}