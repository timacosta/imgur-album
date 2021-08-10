package io.keepcoding.di

import androidx.room.Room
import io.keepcoding.di.DIBaseModule
import io.keepcoding.room.AppDatabase
import io.keepcoding.room.ImageDAO
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object RoomDIModule : DIBaseModule("RoomDIModule") {
    override val builder: DI.Builder.() -> Unit = {
        bind<AppDatabase>() with singleton {
            Room
                .databaseBuilder(instance(), AppDatabase::class.java, "keepcoding")
                .build()
        }
        bind<ImageDAO>() with singleton {
            instance<AppDatabase>().imageDao()
        }
    }
}