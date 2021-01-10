package com.anujan.accenture_n

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anujan.accenture_n.entity.WeatherList
import com.anujan.accenture_n.room.entity.WeatherDetails

class WeatherAdapter (
    val context: Context,
    private val list: List<WeatherDetails>
): RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_row, parent, false)
        return WeatherViewHolder(view)
    }
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        holder.dateAndTime?.text = list[position].dateAndTime
        holder.tempC?.text =list[position].tempC
        holder.description?.text = list[position].description
        holder.windSpeed?.text =list[position].windSpeed
        holder.windDeg?.text =list[position].windDeg
        holder.cloud?.text = list[position].cloud
        holder.visibility?.text = list[position].visibility
        holder.feelsLike?.text = list[position].feelsLike
        holder.tempMax?.text = list[position].tempMax
        holder.tempMin?.text = list[position].tempMin
        holder.pressure?.text = list[position].pressure
        holder.humidity?.text = list[position].humidity
        holder.tempKF?.text = list[position].tempKf
        holder.seaLevel?.text = list[position].seaLevel
        holder.groundLevel?.text = list[position].groundLevel
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class WeatherViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {

    var dateAndTime:TextView? = null
    var tempC:TextView? = null
    var description:TextView? = null
    var windSpeed:TextView? = null
    var windDeg:TextView? = null
    var cloud:TextView? = null
    var visibility:TextView? = null
    var feelsLike:TextView? = null
    var tempMax:TextView? = null
    var tempMin:TextView? = null
    var pressure:TextView? = null
    var humidity:TextView? = null
    var tempKF:TextView? = null
    var seaLevel:TextView? = null
    var groundLevel:TextView? = null

    init {
        dateAndTime = itemView.findViewById(R.id.dateAndTime)
        tempC = itemView.findViewById(R.id.tempC)
        description = itemView.findViewById(R.id.description)
        windSpeed = itemView.findViewById(R.id.windSpeed)
        windDeg = itemView.findViewById(R.id.windDeg)
        cloud = itemView.findViewById(R.id.cloud)
        visibility = itemView.findViewById(R.id.visibility)
        feelsLike = itemView.findViewById(R.id.feelsLike)
        tempMin = itemView.findViewById(R.id.tempMin)
        tempMax = itemView.findViewById(R.id.tempMax)
        pressure = itemView.findViewById(R.id.pressure)
        humidity = itemView.findViewById(R.id.humidity)
        tempKF = itemView.findViewById(R.id.tempKF)
        seaLevel = itemView.findViewById(R.id.seaLevel)
        groundLevel = itemView.findViewById(R.id.groundLevel)
    }

}
