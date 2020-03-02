/**
 * This interface use to get data property
 */
package com.example.samplewithall.repository

import com.example.samplewithall.models.DataProperty
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiService {
    @GET("facts.json")
    fun getPropertiesAsync():
            Call<DataProperty>
}