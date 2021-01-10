package com.anujan.accenture_n.entity

data class WeatherList (

	val dt : Int,
	val main : Main,
	val weather : List<Weather>,
	val clouds : Clouds,
	val wind : Wind,
	val visibility : Int,
	val pop : Double,
	val sys : Sys,
	val dt_txt : String
)