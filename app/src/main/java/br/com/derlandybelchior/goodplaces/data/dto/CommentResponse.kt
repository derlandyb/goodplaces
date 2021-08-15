package br.com.derlandybelchior.goodplaces.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentResponse(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "name") val author: String,
    @field:Json(name = "comment") val comment: String,
    @field:Json(name = "photo") val photoId: Int,
    @field:Json(name = "rating") val review: Float
)
