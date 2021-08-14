package br.com.derlandybelchior.goodplaces.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationBusinessHoursResponse(
    @get:Json(name = "close") val close: String,
    @get:Json(name = "open") val `open`: String
)
