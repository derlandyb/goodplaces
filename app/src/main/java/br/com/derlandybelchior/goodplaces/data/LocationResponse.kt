package br.com.derlandybelchior.goodplaces.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "review") val review: Double,
    @field:Json(name = "type") val type: String
)



@JsonClass(generateAdapter = true)
data class LocationList(
    @field:Json(name = "listLocations") val locationList: List<LocationResponse>
)