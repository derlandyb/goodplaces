package br.com.derlandybelchior.goodplaces.presentation.model

data class PlaceDetailPresentationModel(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val imageUrl: String? = null
)