package br.com.derlandybelchior.goodplaces.domain

import java.time.DayOfWeek

data class LocationBusinessHours(
    val close: String,
    val `open`: String,
    val dayOfWeek: Int
)