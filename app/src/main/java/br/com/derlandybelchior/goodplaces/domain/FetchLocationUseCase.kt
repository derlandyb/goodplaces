package br.com.derlandybelchior.goodplaces.domain

class FetchLocationUseCase(private val locationRepository: LocationRepository) {
    suspend fun fetchAllLocations() : Resource<List<Location>> {
        return try {
            Resource.Success(locationRepository.fetchAll())
        } catch (e: Throwable) {
            Resource.Error(e)
        }
    }

    suspend fun fetch(id: Int): Resource<LocationDetail> {
        return try {
            Resource.Success(locationRepository.fetch(id))
        } catch (e: Throwable) {
            Resource.Error(e)
        }
    }


}