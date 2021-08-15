package br.com.derlandybelchior.goodplaces.domain

data class LocationDetail(
    val id: Int,
    val about: String,
    val address: String,
    val name: String,
    val phone: String,
    val review: Double,
    val schedule: List<Schedule>,
    val type: String,
    val gallery: List<String> = listOf(),
    val comments: List<Comment> = listOf()
)