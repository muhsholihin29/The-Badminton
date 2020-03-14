package com.sstudio.thebadminton.remote

import com.sstudio.thebadminton.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



object RetrofitClient {

    private var retrofitClient: Retrofit? = null

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    fun getClient(): Retrofit {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofitClient as Retrofit
    }
}