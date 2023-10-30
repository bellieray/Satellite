package com.ebelli.core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ebelli.core.model.model.Position
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class SatelliteRoomTypeConverter @Inject constructor() {
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