package com.sstudio.thebadminton2.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sstudio.thebadminton2.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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