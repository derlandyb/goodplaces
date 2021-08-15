package br.com.derlandybelchior.goodplaces.domain

data class Schedule(
    val close: String,
    val `open`: String,
    val dayOfWeek: Int
)