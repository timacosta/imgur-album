package io.keepcoding.room

import androidx.room.TypeConverter
import io.keepcoding.room.RoomImage.ImageType

class AppConverters {

    @TypeConverter
    fun toImageType(value: String) = ImageType.valueOf(value)

    @TypeConverter
    fun fromImageType(imageType: ImageType) =imageType.name
}