package br.com.derlandybelchior.goodplaces.domain

data class Comment(
    val title: String,
    val author: String,
    val comment: String,
    val photoId: Int,
    val review: Float
)
