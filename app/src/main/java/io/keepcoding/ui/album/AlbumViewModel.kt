package io.keepcoding.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.album.Album
import io.keepcoding.album.AlbumRepository
import io.keepcoding.gallery.Image
import io.keepcoding.session.SessionRepository
import io.keepcoding.ui.gallery.GalleryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository
): ViewModel() {

    private val albumStateFlow = MutableStateFlow(AlbumState.empty())
    val albumState: StateFlow<AlbumState>
        get() = albumStateFlow

    private var requestJob: Job? = null


    fun getAlbum(albumHash: String) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            val album = albumRepository.getAlbum(albumHash)
            albumStateFlow.value = AlbumState(album, false)
        }
    }



    data class AlbumState(val album: Album, val hasError: Boolean) {
        companion object {
            fun empty() = AlbumState(Album(emptyList()), false)
        }
    }
}

