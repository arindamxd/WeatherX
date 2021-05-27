package dev.karmakar.weatherx.data.remote

import dev.karmakar.weatherx.data.model.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

interface NetworkService {

    @GET("data/2.5/weather")
    fun searchByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Single<WeatherResponse>
}
