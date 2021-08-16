package io.keepcoding.ui.album

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.gallery.Album
import io.keepcoding.gallery.AlbumImage
import io.keepcoding.gallery.GalleryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(private val galleryRepository: GalleryRepository
): ViewModel() {

    private val albumStateFlow = MutableStateFlow(AlbumState.empty())
    val albumState: StateFlow<AlbumState>
        get() = albumStateFlow

    private var requestJob: Job? = null

    fun getAlbum(albumHash: String) {
        loadAlbum {galleryRepository.getAlbum(albumHash)}
    }

    private fun loadAlbum(alb: suspend () -> Album) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            val album = alb().images
            albumStateFlow.value = AlbumState.transform(album)
        }
    }



    data class AlbumState(val albumImages: List<AlbumImage>, val hasError: Boolean) {
        companion object {
            fun empty() = AlbumState(emptyList(), false)
            fun transform(images: List<AlbumImage>): AlbumState {
                return AlbumState(images, false)
            }
        }
    }
}

