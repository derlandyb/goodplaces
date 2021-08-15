package br.com.derlandybelchior.goodplaces.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleResponse(
    @field:Json(name = "monday")
    val monday: LocationBusinessHoursResponse?,
    @field:Json(name = "tuesday")
    val tuesday: LocationBusinessHoursResponse?,
    @field:Json(name = "wednesday")
    val wednesday: LocationBusinessHoursResponse?,
    @field:Json(name = "thursday")
    val thursday: LocationBusinessHoursResponse?,
    @field:Json(name = "friday")
    val friday: LocationBusinessHoursResponse?,
    @field:Json(name = "saturday")
    val saturday: LocationBusinessHoursResponse?,
    @field:Json(name = "sunday")
    val sunday: LocationBusinessHoursResponse?
)
