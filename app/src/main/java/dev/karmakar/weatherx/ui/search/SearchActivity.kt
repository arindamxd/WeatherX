package dev.karmakar.weatherx.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.karmakar.weatherx.R
import dev.karmakar.weatherx.data.Repository
import dev.karmakar.weatherx.data.local.DatabaseService
import dev.karmakar.weatherx.data.remote.Networking
import dev.karmakar.weatherx.ui.shared.SharedViewModel
import dev.karmakar.weatherx.ui.saved.SavedActivity
import dev.karmakar.weatherx.ui.shared.SharedAdapter
import dev.karmakar.weatherx.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class SearchActivity : AppCompatActivity() {

    companion object {
        const val TAG = "WeatherX"
        const val BASE_URL = "https://api.openweathermap.org/"
        const val API_KEY = "<add your api key>"
        const val DATABASE_NAME = "weather-x-db"
    }

    private lateinit var viewModel: SharedViewModel

    // region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = initViewModel()
        viewModel.observeSearch().observe(this, { weather ->
            weather?.let {
                toggleProgress(false)
                recycler_view.adapter = SharedAdapter(listOf(it)) { weatherId ->
                    viewModel.toggleSaved(weatherId)
                }
            }
        })

        saved.setOnClickListener { startActivity(Intent(this, SavedActivity::class.java)) }
        search.setOnClickListener {
            toggleProgress(true)

            if (!Networking.isNetworkConnected(this)) {
                toggleProgress(false)
                showToast("Internet connection not found.")
                return@setOnClickListener
            }

            val cityName: String = city_name.text.toString()
            if (cityName.isBlank()) {
                toggleProgress(false)
                showToast("Please enter a city name.")
                return@setOnClickListener
            }

            viewModel.search(cityName, API_KEY) { error ->
                toggleProgress(false)
                showToast(error)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.observeSearch().removeObservers(this)
    }

    // endregion Lifecycle

    private fun initViewModel(): SharedViewModel {
        val model: SharedViewModel by viewModels {
            ViewModelFactory(
                Repository(
                    DatabaseService.getInstance(this),
                    Networking.create(BASE_URL)
                )
            )
        }
        return model
    }

    private fun toggleProgress(enable: Boolean) {
        if (enable) {
            progress.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        } else {
            progress.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
