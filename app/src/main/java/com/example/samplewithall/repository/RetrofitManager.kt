/**
 * This class fetch the data from server
 */
package com.example.samplewithall.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplewithall.models.DataProperty
import com.google.gson.GsonBuilder
import com.log4k.d
import com.log4k.e
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Base URL to get the Json Data
private const val BASE_URL =
    "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

class RetrofitManager {

    private val liveUserResponse: MutableLiveData<DataProperty> by lazy { MutableLiveData<DataProperty>() }

    companion object Factory {
        fun create(): RetrofitApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitApiService::class.java)
        }
    }

    fun fetchDataFromServer(): MutableLiveData<DataProperty>? {
        d("Inside fetchDataFromServer")
        val retrofitCall = create().getPropertiesAsync()

        retrofitCall.enqueue(object : Callback<DataProperty> {
            override fun onFailure(call: Call<DataProperty>, t: Throwable?) {
                e("on Failure : retrofit error ${t?.message}")
            }

            override fun onResponse(
                call: Call<DataProperty>,
                response: retrofit2.Response<DataProperty>
            ) {
                d("Response Received ")
                val list = response.body()
                liveUserResponse.value = list
                d("hasActiveObservers : ${liveUserResponse.hasActiveObservers()} check")
            }

        })
        return liveUserResponse
    }
}