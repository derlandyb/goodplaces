package br.com.derlandybelchior.goodplaces.data

import br.com.derlandybelchior.goodplaces.domain.Location
import br.com.derlandybelchior.goodplaces.domain.LocationBusinessHours
import br.com.derlandybelchior.goodplaces.domain.LocationDetail
import br.com.derlandybelchior.goodplaces.domain.Schedule
import java.time.DayOfWeek
import java.util.*
import kotlin.collections.ArrayList

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
            schedule = mapScheduleResponse(locationDetailResponse.schedule),
            phone = locationDetailResponse.phone,
            address = locationDetailResponse.address
        )
    }

    private fun mapScheduleResponse(scheduleResponse: ScheduleResponse) : List<Schedule> {

        val scheduleList = arrayListOf<Schedule>()
        scheduleResponse.monday?.let{ scheduleList.add(mapSchedule(scheduleResponse.monday, Calendar.MONDAY)) }
        scheduleResponse.tuesday?.let{ scheduleList.add(mapSchedule(scheduleResponse.tuesday, Calendar.TUESDAY)) }
        scheduleResponse.wednesday?.let{ scheduleList.add(mapSchedule(scheduleResponse.wednesday, Calendar.WEDNESDAY)) }
        scheduleResponse.thursday?.let{ scheduleList.add(mapSchedule(scheduleResponse.thursday, Calendar.THURSDAY)) }
        scheduleResponse.friday?.let{ scheduleList.add(mapSchedule(scheduleResponse.friday, Calendar.FRIDAY)) }
        scheduleResponse.saturday?.let{ scheduleList.add(mapSchedule(scheduleResponse.saturday, Calendar.SATURDAY)) }
        scheduleResponse.sunday?.let{ scheduleList.add(mapSchedule(scheduleResponse.sunday, Calendar.SUNDAY)) }

        return scheduleList
    }

    private fun mapSchedule(businessHoursResponse: LocationBusinessHoursResponse, dayOfWeek: Int) = Schedule(
        open = businessHoursResponse.open,
        close = businessHoursResponse.close,
        dayOfWeek = dayOfWeek
    )
}