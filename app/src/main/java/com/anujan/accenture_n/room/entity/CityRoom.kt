package com.anujan.accenture_n.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "City")
data class CityRoom (
    val cityName : String,
    val sunrise : String,
    val sunset : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //**do not made it val**
}