package br.com.derlandybelchior.goodplaces.data

import br.com.derlandybelchior.goodplaces.BuildConfig
import br.com.derlandybelchior.goodplaces.data.service.LocationServiceAPI
import br.com.derlandybelchior.goodplaces.data.service.PhotoLocationServiceAPI
import br.com.derlandybelchior.goodplaces.data.service.ReviewsLocationServiceAPI
import br.com.derlandybelchior.goodplaces.domain.Comment
import br.com.derlandybelchior.goodplaces.domain.Location
import br.com.derlandybelchior.goodplaces.domain.LocationDetail
import br.com.derlandybelchior.goodplaces.domain.LocationRepository

class RemoteRepository(
    private val locationServiceAPI: LocationServiceAPI,
    private val photoLocationServiceAPI: PhotoLocationServiceAPI,
    private val reviewsLocationServiceAPI: ReviewsLocationServiceAPI
) : LocationRepository {

    override suspend fun fetchAll(): List<Location> {
        val list = locationServiceAPI.fetchAll().locationList

        return if(list.isNotEmpty()) list.map { getPhotoUrlForItem(it) } else listOf()
    }

    override suspend fun fetch(id: Int): LocationDetail {
        val locationDetail = getPhotoUrlForGallery(locationServiceAPI.fetch(id))
        val comments = getAllComments()

        return locationDetail.copy(comments = comments)
    }

    private suspend fun getAllComments(): List<Comment> {
        return try {
            reviewsLocationServiceAPI.getComments().map {
                Comment(
                    title = it.title,
                    author = it.author,
                    comment = it.comment,
                    photoId = it.photoId,
                    review = it.review
                )
            }
        } catch (e: Throwable) {
            listOf()
        }
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

    private suspend fun getPhotoUrlForGallery(locationDetailResponse: LocationDetailResponse): LocationDetail {
        val location = LocationMapper.map(locationDetailResponse)
        return try {
            val result = photoLocationServiceAPI.searchPhotos(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                criteria = location.type,
                pageSize = 10
            )

            if(result.total > 0) {
                val galleryUrls = arrayListOf<String>()
                result.results.map{ it.urls.small?.let { url -> galleryUrls.add(url) }}
                location.copy(gallery = galleryUrls)
            } else {
                location
            }

        } catch (e: Throwable) {
            location
        }
    }
}