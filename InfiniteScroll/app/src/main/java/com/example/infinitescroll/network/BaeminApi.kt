package com.example.infinitescroll.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BaeminApi {
    private const val baseUrl = "https://ceo.baemin.com/cms/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi(): BaeminApiService{
        return retrofit.create(BaeminApiService::class.java)
    }
}