package br.com.derlandybelchior.goodplaces.data

import br.com.derlandybelchior.goodplaces.BuildConfig
import br.com.derlandybelchior.goodplaces.data.service.LocationServiceAPI
import br.com.derlandybelchior.goodplaces.data.service.PhotoLocationServiceAPI
import br.com.derlandybelchior.goodplaces.domain.Location
import br.com.derlandybelchior.goodplaces.domain.LocationDetail
import br.com.derlandybelchior.goodplaces.domain.LocationRepository

class RemoteRepository(
    private val locationServiceAPI: LocationServiceAPI,
    private val photoLocationServiceAPI: PhotoLocationServiceAPI
) : LocationRepository {

    override suspend fun fetchAll(): List<Location> {
        val list = locationServiceAPI.fetchAll().locationList

        return if(list.isNotEmpty()) list.map { getPhotoUrlForItem(it) } else listOf()
    }

    override suspend fun fetch(id: Int): LocationDetail {
        return LocationMapper.map(locationServiceAPI.fetch(id))
    }

    private suspend fun getPhotoUrlForItem(locationResponse: LocationResponse): Location {
        val location = LocationMapper.map(locationResponse)
        return try {
            val result = photoLocationServiceAPI.searchPhotos(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                criteria = location.type
            )

            if(result.total > 0 && result.results.first().urls.small != null) {
                    location.copy(imageUrl = result.results.first().urls.small)
            } else {
                location
            }

        } catch (e: Throwable) {
            location
        }
    }
}