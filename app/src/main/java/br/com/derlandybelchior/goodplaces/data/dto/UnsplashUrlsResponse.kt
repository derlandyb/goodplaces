package br.com.derlandybelchior.goodplaces.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashUrlsResponse(
    @field:Json(name = "thumb") val thumb: String?,
    @field:Json(name = "small") val small: String?,
    @field:Json(name = "medium") val medium: String?,
    @field:Json(name = "regular") val regular: String?,
    @field:Json(name = "large") val large: String?,
    @field:Json(name = "full") val full: String?,
    @field:Json(name = "raw") val raw: String?
)