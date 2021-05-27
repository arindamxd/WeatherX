package dev.karmakar.weatherx.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.karmakar.weatherx.data.local.DatabaseService
import dev.karmakar.weatherx.data.model.room.Weather
import dev.karmakar.weatherx.data.remote.NetworkService
import dev.karmakar.weatherx.ui.search.SearchActivity
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class Repository constructor(
    private val databaseService: DatabaseService,
    private val networkService: NetworkService
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun search(cityName: String, apiKey: String, error: (String) -> Unit) {
        compositeDisposable.add(networkService.searchByCityName(cityName, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                val dao = databaseService.weatherDao()
                val record = response.getEntity()
                val existingRecord = dao.getRecord(record.id)
                if (existingRecord != null) record.isSaved = existingRecord.isSaved // Edge Case
                dao.insert(record)
            }, { ex ->
                Log.e(SearchActivity.TAG, "Error -> ${ex.message}")
                Handler(Looper.getMainLooper()).post { error("City not found.") }
            })
        )
    }

    fun toggleSaved(id: Long) {
        compositeDisposable.add(Completable.fromAction {
            databaseService.weatherDao().toggleSaved(id)
        }.subscribeOn(Schedulers.io()).subscribe())
    }

    fun observeSearch(): LiveData<Weather> = databaseService.weatherDao().observeSearch()
    fun observeSaved(): LiveData<List<Weather>> = databaseService.weatherDao().observeSaved()

    fun clear() = compositeDisposable.clear()
}
