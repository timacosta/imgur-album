package io.keepcoding.di

import io.keepcoding.di.DIBaseModule
import io.keepcoding.session.SessionLocalDataSource
import io.keepcoding.session.SessionMemoryDataSource
import io.keepcoding.session.SessionRepository
import io.keepcoding.session.SessionRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object SessionDIModule : DIBaseModule("SessionDIModule") {
    override val builder: DI.Builder.() -> Unit = {

        bind<SessionLocalDataSource>() with singleton {
            SessionLocalDataSource(instance())
        }

        bind<SessionMemoryDataSource>() with singleton {
            SessionMemoryDataSource()
        }

        bind<SessionRepository>() with singleton {
                SessionRepositoryImpl(instance(), instance())
        }
    }
}