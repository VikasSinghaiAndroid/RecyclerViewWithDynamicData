/**
 * Model class for Json Data
 */
package com.example.recyclerviewwithdynamicdata.network.models

import com.example.recyclerviewwithdynamicdata.network.models.Row
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataPropertyParser(
    @Json(name = "title") val title: String? = "Main title is null",
    @Json(name = "rows") val rows: List<Row>
)