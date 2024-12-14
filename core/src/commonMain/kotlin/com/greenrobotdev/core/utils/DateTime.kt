package com.greenrobotdev.core.utils

import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val Instant.date: LocalDate get() = this.toLocalDateTime(TimeZone.currentSystemDefault()).date
val Instant.time: LocalTime get() = this.toLocalDateTime(TimeZone.currentSystemDefault()).time

val DayOfWeek.text: String @Composable get() = name.lowercase().replaceFirstChar { it.uppercase() }
val Month.text: String @Composable get() = name.lowercase().replaceFirstChar { it.titlecase() }

val LocalDate.text: String
    @Composable get() {

        val timeZone: TimeZone = TimeZone.currentSystemDefault()
        val today: LocalDate = Clock.System.now().toLocalDateTime(timeZone).date

        return when {
            today.year == this.year -> "${month.text.substring(0..2)} $dayOfMonth"
            else -> "${month.text.substring(0..2)} $dayOfMonth, $year"
        }
    }

//@Composable
//fun LocalDate.dateRangeFormat(other: LocalDate?): String? {
//    val timeZone: TimeZone = TimeZone.currentSystemDefault()
//    val today: LocalDate = Clock.System.now().toLocalDateTime(timeZone).date
//    if (other == null) return other?.date?.text
//    return when {
//        this.month == today.month && other.month == today.month
//        -> "${this.month.text.substring(0..2)} ${this.dayOfMonth} - ${other.dayOfMonth}"
//
//        startDate.date.year == today.year && endDate.date.year != today.year
//        -> "${startDate.date.text} - ${endDate.date.text}"
//
//        else -> ""
//    }
//}
//