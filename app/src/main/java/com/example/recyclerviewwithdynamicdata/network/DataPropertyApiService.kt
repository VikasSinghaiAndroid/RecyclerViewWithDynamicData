/**
 * This interface use to get data property
 */
package com.example.recyclerviewwithdynamicdata.network

import retrofit2.Call
import retrofit2.http.GET

interface DataPropertyApiService {
    @GET("facts.json")
    fun getProperties():
            Call<DataPropertyParser>
}