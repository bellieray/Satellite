package com.ebelli.core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ebelli.core.model.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class SatelliteRoomTypeConverter @Inject constructor(private val gson: Gson) {
    @TypeConverter
    fun fromPositionString(value: String?): List<Position?> {
        val listType = object : TypeToken<ArrayList<Position?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromPositionList(list: List<Position?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPosition(position: Position?): String? {
        return position?.let {
            Gson().toJson(it)
        }
    }

    @TypeConverter
    fun toPosition(positionJson: String?): Position? {
        return positionJson?.let {
            Gson().fromJson(it, Position::class.java)
        }
    }
}