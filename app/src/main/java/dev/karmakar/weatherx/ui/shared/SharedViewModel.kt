package dev.karmakar.weatherx.ui.shared

import androidx.lifecycle.ViewModel
import dev.karmakar.weatherx.data.Repository

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class SharedViewModel(private val repository: Repository) : ViewModel() {

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }

    fun search(
        cityName: String,
        apiKey: String,
        error: (String) -> Unit
    ) = repository.search(cityName, apiKey, error)
    fun toggleSaved(id: Long) = repository.toggleSaved(id)

    fun observeSearch() = repository.observeSearch()
    fun observeSaved() = repository.observeSaved()
}
