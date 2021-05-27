package dev.karmakar.weatherx.data.model.response

import dev.karmakar.weatherx.data.model.room.Weather

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

data class WeatherResponse(
    val id: Long,
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val sys: Sys,
    val clouds: Clouds,
    val wind: Wind,
    val dt: Long
) {

    // region Inner Data Classes

    data class Weather(
        val main: String,
        val description: String
    )

    data class Main(
        val temp: Float,
        val feels_like: Float,
        val temp_min: Float,
        val temp_max: Float,
        val humidity: Int
    )

    data class Sys(
        val country: String
    )

    data class Clouds(
        val all: Int
    )

    data class Wind(
        val speed: Float
    )

    // endregion Inner Data Classes

    // region Getter / Converter

    fun getEntity() = Weather(
        id = id,
        name = name,
        condition = weather.first().main,
        description = weather.first().description,
        temp = main.temp,
        feels_like = main.feels_like,
        temp_min = main.temp_min,
        temp_max = main.temp_max,
        country = sys.country,
        humidity = main.humidity,
        cloudiness = clouds.all,
        windSpeed = wind.speed,
        last_updated = dt
    )

    // endregion Getter / Converter
}
