package dev.karmakar.weatherx.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table WHERE saved = 1 ORDER BY timestamp DESC")
    fun observeSaved(): LiveData<List<Weather>>

    @Query("SELECT * FROM weather_table ORDER BY timestamp DESC")
    fun observeSearch(): LiveData<Weather>

    @Query("SELECT * FROM weather_table WHERE id = :id LIMIT 1")
    fun getRecord(id: Long): Weather?

    @Query("UPDATE weather_table SET saved = NOT saved WHERE id = :id")
    fun toggleSaved(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)
}
