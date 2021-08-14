package br.com.derlandybelchior.goodplaces.data.service

import br.com.derlandybelchior.goodplaces.data.LocationDetailResponse
import br.com.derlandybelchior.goodplaces.data.LocationList
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationServiceAPI {
    @GET("locations")
    suspend fun fetchAll() : LocationList

    @GET("locations/{id}")
    suspend fun fetch(@Path("id") id: Int) : LocationDetailResponse
}