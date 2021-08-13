package br.com.derlandybelchior.goodplaces.domain

interface LocationRepository {
    suspend fun fetchAll() : List<Location>
    suspend fun fetch(id: Int) : LocationDetail
}