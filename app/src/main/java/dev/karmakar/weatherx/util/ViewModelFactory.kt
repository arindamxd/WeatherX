package dev.karmakar.weatherx.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.karmakar.weatherx.data.Repository
import dev.karmakar.weatherx.ui.shared.SharedViewModel

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}
