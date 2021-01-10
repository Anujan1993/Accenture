package com.anujan.accenture_n.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anujan.accenture_n.room.entity.WeatherDetails

@Dao
interface WeatherDao {
    @Query("SELECT * FROM Weather WHERE city IN (:city)")
    fun loadAllByIds(city: String): List<WeatherDetails>

    @Insert
    fun insertAll(vararg weather: WeatherDetails)

    @Query("DELETE FROM Weather WHERE city IN(:city)")
    fun deleteAll(city: String)
}