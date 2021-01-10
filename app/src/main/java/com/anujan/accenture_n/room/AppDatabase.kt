package com.anujan.accenture_n.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anujan.accenture_n.room.dao.CityDao
import com.anujan.accenture_n.room.dao.WeatherDao
import com.anujan.accenture_n.room.entity.CityRoom
import com.anujan.accenture_n.room.entity.WeatherDetails

@Database(entities = [CityRoom::class,WeatherDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao():WeatherDao
}