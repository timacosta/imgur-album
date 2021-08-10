package io.keepcoding.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.keepcoding.room.RoomImage

@Database(entities = [RoomImage::class], version = 2)
@TypeConverters(value = [AppConverters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDAO
}