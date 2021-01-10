package com.anujan.accenture_n.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Weather")
data class WeatherDetails(
    val city:String,
    val dateAndTime: String,
    val tempC: String,
    val description:String,
    val windSpeed:String,
    val windDeg:String,
    val cloud:String,
    val visibility:String,
    val tempMin:String,
    val tempMax:String,
    val feelsLike:String,
    val pressure:String,
    val humidity:String,
    val tempKf:String,
    val seaLevel:String,
    val groundLevel:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //**do not made it val**
}