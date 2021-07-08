package com.example.retrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaeminClient {
    private const val baseUrl = "https://ceo.baemin.com/cms/v1/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(BaeminService::class.java)!!
}