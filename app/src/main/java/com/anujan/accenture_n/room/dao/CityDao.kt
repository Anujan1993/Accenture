package com.anujan.accenture_n.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anujan.accenture_n.room.entity.CityRoom

@Dao
interface CityDao {
    @Query("SELECT * FROM City WHERE cityName IN (:city)")
    fun loadAllByIds(city: String): CityRoom

    @Insert
    fun insertAll(vararg city: CityRoom)

    @Query("DELETE FROM City WHERE cityName IN(:city)")
    fun deleteAllCity(city: String)
}