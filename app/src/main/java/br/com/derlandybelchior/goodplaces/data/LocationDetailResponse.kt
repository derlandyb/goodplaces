package br.com.derlandybelchior.goodplaces.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDetailResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "about")
    val about: String,
    @field:Json(name = "adress")
    val address: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "review")
    val review: Double,
    @field:Json(name = "schedule")
    val schedule: ScheduleResponse,
    @field:Json(name = "type")
    val type: String
)