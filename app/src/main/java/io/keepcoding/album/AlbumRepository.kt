package io.keepcoding.album

interface AlbumRepository {

    suspend fun getAlbum(albumHash: String): Album

}