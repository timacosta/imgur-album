package io.keepcoding.ui.album

import io.keepcoding.album.AlbumRepository
import io.keepcoding.gallery.Image
import io.keepcoding.session.SessionRepository
import io.keepcoding.ui.gallery.GalleryViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlbumViewModel(private val albumRepository: AlbumRepository) {

    private val albumStateFlow = MutableStateFlow(AlbumState.empty())
    val albumState: StateFlow<AlbumState>
        get() = albumStateFlow



    data class AlbumState(val images: List<Image>, val hasError: Boolean) {
        companion object {
            fun empty() = AlbumState(emptyList(), false)
        }
    }
}

