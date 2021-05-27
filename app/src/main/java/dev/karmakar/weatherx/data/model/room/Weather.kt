package dev.karmakar.weatherx.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "condition") val condition: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "temp") val temp: Float,
    @ColumnInfo(name = "feels_like") val feels_like: Float,
    @ColumnInfo(name = "temp_min") val temp_min: Float,
    @ColumnInfo(name = "temp_max") val temp_max: Float,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "cloudiness") val cloudiness: Int,
    @ColumnInfo(name = "windSpeed") val windSpeed: Float,
    @ColumnInfo(name = "last_updated") val last_updated: Long,
    @ColumnInfo(name = "saved") var isSaved: Boolean = false, // Favorite
    @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis()
)
