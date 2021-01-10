package com.anujan.accenture_n.entity

data class CityWeatherMain (

	val cod : Int,
	val message : Int,
	val cnt : Int,
	val list : List<WeatherList>,
	val city : City
)