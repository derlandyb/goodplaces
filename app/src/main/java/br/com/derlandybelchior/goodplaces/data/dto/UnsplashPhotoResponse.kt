package br.com.derlandybelchior.goodplaces.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashPhotoResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "color")
    val color: String? = "#000000",
    @field:Json(name = "likes")
    val likes: Int,
    @field:Json(name = "description")
    val description: String?,
    val urls: UnsplashUrlsResponse
)
