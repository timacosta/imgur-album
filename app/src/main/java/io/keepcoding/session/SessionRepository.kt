package io.keepcoding.session
interface SessionRepository {

    fun getSession(): Session?

    fun saveSession(session: Session)

}