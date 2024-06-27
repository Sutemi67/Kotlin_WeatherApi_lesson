package com.example.weather.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.retrofit.ForecastLocation

class LocationViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.location_list_item, parent, false)) {

    var name: TextView = itemView.findViewById(R.id.locationName)

    @SuppressLint("SetTextI18n")
    fun bind(location: ForecastLocation) {
        name.text = "${location.name} (${location.country})"
    }
}

class LocationsAdapter(private val clickListener: LocationClickListener) : RecyclerView.Adapter<LocationViewHolder>() {

    var locations = ArrayList<ForecastLocation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder = LocationViewHolder(parent)

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations.get(position))
        holder.itemView.setOnClickListener { clickListener.onLocationClick(locations.get(position)) }
    }

    override fun getItemCount(): Int = locations.size

    fun interface LocationClickListener {
        fun onLocationClick(location: ForecastLocation)
    }
}