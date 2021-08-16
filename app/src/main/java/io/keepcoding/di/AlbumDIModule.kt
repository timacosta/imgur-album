package io.keepcoding.di

import io.keepcoding.album.AlbumRepository
import io.keepcoding.album.AlbumRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object AlbumDIModule: DIBaseModule("AlbumDIModule") {
    override val builder: DI.Builder.() -> Unit = {
        bind<AlbumRepository>() with singleton {
            AlbumRepositoryImpl(instance())
        }
    }
}