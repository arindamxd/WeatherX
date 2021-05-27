package dev.karmakar.weatherx.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.karmakar.weatherx.R
import dev.karmakar.weatherx.data.model.room.Weather

/**
 * Created by Arindam Karmakar on 27/05/21.
 */

class SharedAdapter(
    private val weatherList: List<Weather>,
    private val onSaved: (Long) -> Unit
) : RecyclerView.Adapter<SharedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_item, parent, false)
        return SharedViewHolder(view, onSaved)
    }

    override fun onBindViewHolder(holder: SharedViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}
