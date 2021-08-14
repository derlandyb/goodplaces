package br.com.derlandybelchior.goodplaces.data.service

import br.com.derlandybelchior.goodplaces.data.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoLocationServiceAPI {

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") criteria: String,
        @Query("page") page: Int = 1,
        @Query("per_page") pageSize: Int = 1
    ): SearchResponse
}