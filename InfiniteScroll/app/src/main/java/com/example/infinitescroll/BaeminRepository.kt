package com.example.infinitescroll

import androidx.lifecycle.MutableLiveData
import com.example.infinitescroll.network.BaeminApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaeminRepository {
    val _notice = MutableLiveData<Data>()

    fun loadBaeminNotice(page: Int){
        val call = BaeminApi.createApi().loadNotice(page.toString())

        call.enqueue(object : Callback<Baemin>{
            override fun onResponse(call: Call<Baemin>, response: Response<Baemin>) {
                _notice.value = response.body()!!.data
            }

            override fun onFailure(call: Call<Baemin>, t: Throwable) {

            }

        })
    }

}