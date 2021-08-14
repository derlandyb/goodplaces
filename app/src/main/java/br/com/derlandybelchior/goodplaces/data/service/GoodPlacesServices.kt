package br.com.derlandybelchior.goodplaces.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GoodPlacesServices {

    fun retrofit(baseUrl: String): Retrofit {

        return Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}