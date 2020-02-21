package com.example.recyclerviewwithdynamicdata.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DataPropertyApiService {
    @GET("facts.json")
    fun getProperties():
            Deferred<List<DataPropertyParser>>
}