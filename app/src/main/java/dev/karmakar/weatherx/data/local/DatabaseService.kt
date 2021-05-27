package dev.karmakar.weatherx.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.karmakar.weatherx.data.model.room.Weather
import dev.karmakar.weatherx.data.model.room.WeatherDao
import dev.karmakar.weatherx.ui.search.SearchActivity

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

@Database(entities = [Weather::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: DatabaseService? = null

        @JvmStatic
        fun getInstance(context: Context): DatabaseService = synchronized(this) {
            if (instance == null) instance = buildDatabase(context)
            return instance as DatabaseService
        }

        private fun buildDatabase(context: Context): DatabaseService = Room.databaseBuilder(
            context,
            DatabaseService::class.java,
            SearchActivity.DATABASE_NAME
        ).build()
    }

    abstract fun weatherDao(): WeatherDao
}
