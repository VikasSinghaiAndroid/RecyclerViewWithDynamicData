package com.example.recyclerviewwithdynamicdata.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataPropertyParser (
    @Json(name = "title") val title: String,
    @Json(name = "rows") val rows: List<Row>
)