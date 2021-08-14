package br.com.derlandybelchior.goodplaces.data

import br.com.derlandybelchior.goodplaces.domain.Location
import br.com.derlandybelchior.goodplaces.domain.LocationBusinessHours
import br.com.derlandybelchior.goodplaces.domain.LocationDetail
import br.com.derlandybelchior.goodplaces.domain.Schedule

object LocationMapper {

    fun mapAll(locationResponseList: List<LocationResponse>) : List<Location> {
        return locationResponseList.map { map(it) }
    }

    fun map(locationResponse: LocationResponse) : Location {
        return Location(
            id = locationResponse.id,
            name = locationResponse.name,
            review = locationResponse.review,
            type = locationResponse.type
        )
    }

    fun map(locationDetailResponse: LocationDetailResponse) : LocationDetail {
        return LocationDetail(
            id = locationDetailResponse.id,
            name = locationDetailResponse.name,
            review = locationDetailResponse.review,
            type = locationDetailResponse.type,
            about = locationDetailResponse.about,
            schedule = mapSchedule(locationDetailResponse.schedule),
            phone = locationDetailResponse.phone,
            address = locationDetailResponse.address
        )
    }

    private fun mapSchedule(scheduleResponse: ScheduleResponse) : Schedule {
        return Schedule(
            monday = mapBusinessHours(scheduleResponse.monday),
            tuesday = mapBusinessHours(scheduleResponse.tuesday),
            wednesday = mapBusinessHours(scheduleResponse.wednesday),
            thursday = mapBusinessHours(scheduleResponse.thursday),
            friday = mapBusinessHours(scheduleResponse.friday),
            saturday = mapBusinessHours(scheduleResponse.saturday),
            sunday = mapBusinessHours(scheduleResponse.sunday),
        )
    }

    private fun mapBusinessHours(businessHoursResponse: LocationBusinessHoursResponse) = LocationBusinessHours(
        open = businessHoursResponse.open,
        close = businessHoursResponse.close
    )
}