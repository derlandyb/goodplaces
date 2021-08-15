package br.com.derlandybelchior.goodplaces.data.service

import br.com.derlandybelchior.goodplaces.data.dto.CommentResponse
import retrofit2.http.GET

interface ReviewsLocationServiceAPI {

    @GET("comments.json")
    suspend fun getComments(): List<CommentResponse>
}