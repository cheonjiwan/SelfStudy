package com.example.infinitescroll.network

import com.example.infinitescroll.Baemin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaeminApiService {
    @GET("contents?typeCode=notice&size=10")
    fun loadNotice(@Query("page") page: String): Call<Baemin>
}

