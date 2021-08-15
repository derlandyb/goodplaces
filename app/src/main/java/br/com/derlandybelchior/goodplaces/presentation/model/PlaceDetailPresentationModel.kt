package br.com.derlandybelchior.goodplaces.presentation.model

import android.content.Context
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.domain.Schedule
import java.util.*

import kotlin.text.StringBuilder

data class PlaceDetailPresentationModel(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedule: List<Schedule>,
    val gallery: List<String> = listOf(),
    val comments: List<CommentPresentationModel> = listOf()
)

fun PlaceDetailPresentationModel.formatSchedulePresentation(context: Context): String {
    return if(schedule.isNotEmpty()) {
        val weekdays = context.resources.getStringArray(R.array.weekdays)
        val stringBuilder = StringBuilder()

        val noWeekendDays = schedule.sortedBy {
            it.dayOfWeek
        }.filter {
            it.dayOfWeek != Calendar.SATURDAY && it.dayOfWeek != Calendar.SUNDAY
        }

        val weekendDays = schedule.sortedBy {
            it.dayOfWeek
        }.filter {
            it.dayOfWeek == Calendar.SATURDAY || it.dayOfWeek == Calendar.SUNDAY
        }.reversed()

        if(noWeekendDays.isNotEmpty()) {
            if(noWeekendDays.size > 1) {
                val firstSchedule = noWeekendDays.first()
                val lastSchedule = noWeekendDays.last()

                stringBuilder.append(weekdays[firstSchedule.dayOfWeek - 1])
                stringBuilder.append(" a ")
                stringBuilder.append(weekdays[lastSchedule.dayOfWeek - 1])
                stringBuilder.append(":")
                stringBuilder.append(firstSchedule.open)
                stringBuilder.append(" às ")
                stringBuilder.append(firstSchedule.close)
                stringBuilder.append("\n")
            }
        }

        if(weekendDays.isNotEmpty()) {
            val firstWeekendSchedule = weekendDays.first()
            stringBuilder.append(weekdays[firstWeekendSchedule.dayOfWeek - 1])
            if(weekendDays.size > 1) {
                val lastWeekendSchedule = weekendDays.last()
                stringBuilder.append(" e ")
                stringBuilder.append(weekdays[lastWeekendSchedule.dayOfWeek - 1])
            }

            stringBuilder.append(":")
            stringBuilder.append(firstWeekendSchedule.open)
            stringBuilder.append(" às ")
            stringBuilder.append(firstWeekendSchedule.close)
        }

        stringBuilder.toString()
    } else {
        String()
    }
}