package dev.karmakar.weatherx.util

import java.util.*
import kotlin.math.roundToInt

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

fun Float.convertToCelsius(): Int = (this - 273.15F).roundToInt() // Kelvin to Celsius

fun Long.getElapsedTime(): String { // Elapsed Time
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = Date(this * 1000).time

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND)

    val currentCalendar = Calendar.getInstance()
    val currentYear = currentCalendar.get(Calendar.YEAR)
    val currentMonth = currentCalendar.get(Calendar.MONTH)
    val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
    val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = currentCalendar.get(Calendar.MINUTE)
    val currentSecond = currentCalendar.get(Calendar.SECOND)

    return when {
        year < currentYear -> {
            val interval = currentYear - year
            if (interval == 1) "$interval year ago" else "$interval years ago"
        }
        month < currentMonth -> {
            val interval = currentMonth - month
            if (interval == 1) "$interval month ago" else "$interval months ago"
        }
        day < currentDay -> {
            val interval = currentDay - day
            if (interval == 1) "$interval day ago" else "$interval days ago"
        }
        hour < currentHour -> {
            val interval = currentHour - hour
            if (interval == 1) "$interval hour ago" else "$interval hours ago"
        }
        minute < currentMinute -> {
            val interval = currentMinute - minute
            if (interval == 1) "$interval minute ago" else "$interval minutes ago"
        }
        second < currentSecond -> {
            val interval = currentSecond - second
            if (interval == 1) "a moment ago" else "few seconds ago"
        }
        else -> "just now"
    }
}
