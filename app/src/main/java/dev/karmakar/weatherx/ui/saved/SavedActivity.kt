package dev.karmakar.weatherx.ui.saved

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.karmakar.weatherx.R
import dev.karmakar.weatherx.data.Repository
import dev.karmakar.weatherx.data.local.DatabaseService
import dev.karmakar.weatherx.data.remote.Networking
import dev.karmakar.weatherx.ui.search.SearchActivity
import dev.karmakar.weatherx.ui.shared.SharedAdapter
import dev.karmakar.weatherx.ui.shared.SharedViewModel
import dev.karmakar.weatherx.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.android.synthetic.main.activity_search.progress
import kotlinx.android.synthetic.main.activity_search.recycler_view

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class SavedActivity : AppCompatActivity() {

    private lateinit var viewModel: SharedViewModel

    // region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        viewModel = initViewModel()
        viewModel.observeSaved().observe(this, { weatherList ->
            toggleProgress(false)
            recycler_view.adapter = SharedAdapter(weatherList) { weatherId ->
                viewModel.toggleSaved(weatherId)
            }
        })

        back.setOnClickListener { onBackPressed() }
    }

    // endregion Lifecycle

    private fun initViewModel(): SharedViewModel {
        val model: SharedViewModel by viewModels {
            ViewModelFactory(Repository(
                DatabaseService.getInstance(this),
                Networking.create(SearchActivity.BASE_URL)
            ))
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
}
