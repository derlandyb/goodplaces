package br.com.derlandybelchior.goodplaces.presentation.model

data class CommentPresentationModel(
    val title: String,
    val author: String,
    val comment: String,
    val photoUrl: String? = null,
    val review: Float
)
