package br.com.derlandybelchior.goodplaces.domain

data class LocationDetail(
    val id: Int,
    val about: String,
    val address: String,
    val name: String,
    val phone: String,
    val review: Double,
    val schedule: Schedule,
    val type: String
)