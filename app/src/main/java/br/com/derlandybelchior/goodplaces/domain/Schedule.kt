package br.com.derlandybelchior.goodplaces.domain

data class Schedule(
    val friday: LocationBusinessHours,
    val monday: LocationBusinessHours,
    val saturday: LocationBusinessHours,
    val sunday: LocationBusinessHours,
    val thursday: LocationBusinessHours,
    val tuesday: LocationBusinessHours,
    val wednesday: LocationBusinessHours
)