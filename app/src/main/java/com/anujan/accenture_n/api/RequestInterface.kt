package com.anujan.accenture_n.api

import com.anujan.accenture_n.entity.CityWeatherMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {
    @GET("forecast")
    fun getWeather(
        @Query("APPID") key: String?,
        @Query("q") city: String?
    ): Call<CityWeatherMain>
}