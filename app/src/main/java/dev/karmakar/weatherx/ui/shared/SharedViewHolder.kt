package dev.karmakar.weatherx.ui.shared

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.karmakar.weatherx.R
import dev.karmakar.weatherx.data.model.room.Weather
import dev.karmakar.weatherx.util.convertToCelsius
import dev.karmakar.weatherx.util.getElapsedTime

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class SharedViewHolder(
    itemView: View,
    private val onSaved: (Long) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name)
    private val tempTextView: TextView = itemView.findViewById(R.id.temp)
    private val tempDescTextView: TextView = itemView.findViewById(R.id.temp_description)
    private val feelsLikeTextView: TextView = itemView.findViewById(R.id.feels_like)
    private val humidityTextView: TextView = itemView.findViewById(R.id.humidity)
    private val cloudinessTextView: TextView = itemView.findViewById(R.id.cloudiness)
    private val windSpeedTextView: TextView = itemView.findViewById(R.id.wind_speed)
    private val lastUpdatedTextView: TextView = itemView.findViewById(R.id.last_updated)
    private val savedIconImageView: ImageView = itemView.findViewById(R.id.saved_icon)

    fun bind(weather: Weather) {

        val place = "${weather.name}, ${weather.country}"
        val temp = "${weather.temp.convertToCelsius()}°" // Kelvin to Celsius
        val tempMax = weather.temp_max.convertToCelsius().toString()
        val tempMin = "${weather.temp_min.convertToCelsius()}°C"
        val tempDesc = "${weather.condition}\nH:$tempMax / L:$tempMin"
        val feelsLike = "Feels like ${weather.feels_like.convertToCelsius()}°C"
        val humidity = "Humidity ${weather.humidity}%"
        val cloudiness = "Cloudiness ${weather.cloudiness}%"
        val windSpeed = "Wind speed ${weather.windSpeed} meter/sec"
        val lastUpdated = "Last updated ${weather.last_updated.getElapsedTime()}"
        val icon = if (weather.isSaved) R.drawable.ic_favorite_dark_filled else R.drawable.ic_favorite_dark

        nameTextView.text = place
        tempTextView.text = temp
        tempDescTextView.text = tempDesc
        feelsLikeTextView.text = feelsLike
        humidityTextView.text = humidity
        cloudinessTextView.text = cloudiness
        windSpeedTextView.text = windSpeed
        lastUpdatedTextView.text = lastUpdated

        savedIconImageView.setImageResource(icon)
        savedIconImageView.setOnClickListener { onSaved(weather.id) }
    }
}
