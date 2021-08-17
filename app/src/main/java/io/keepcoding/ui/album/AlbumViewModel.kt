package io.keepcoding.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.gallery.Album
import io.keepcoding.gallery.AlbumImage
import io.keepcoding.gallery.GalleryRepository
import kotlinx.coroutines.Dispatchers
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

    fun getAlbum(id: String) {
        loadAlbum {galleryRepository.getAlbum(id)}
    }

    private fun loadAlbum(album: suspend () -> Album) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch(Dispatchers.IO) {
            albumStateFlow.value = AlbumState.empty()
            val albums = album().images
            albumStateFlow.value = AlbumState.transform(albums)
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

