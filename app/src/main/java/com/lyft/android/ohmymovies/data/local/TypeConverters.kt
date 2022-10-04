package com.lyft.android.ohmymovies.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<Int> {
        return value?.split("/").orEmpty().mapNotNull { it.toIntOrNull() }
    }

    @TypeConverter
    fun listOfIntToString(ints: List<Int>): String {
        return ints.joinToString(separator = "/")
    }
}