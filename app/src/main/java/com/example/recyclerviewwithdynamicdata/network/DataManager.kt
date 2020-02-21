package com.example.recyclerviewwithdynamicdata.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Base URL to get the Json Data
private const val BASE_URL =
    "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

// Creating Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Creating Retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Call DataPropertyApiService to get the data
 */
object DataParseApi {
    val retrofitService: DataPropertyApiService by lazy {
        retrofit.create(DataPropertyApiService::class.java)
    }
}