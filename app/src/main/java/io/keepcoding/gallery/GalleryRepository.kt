package io.keepcoding.gallery

import io.keepcoding.gallery.Gallery

interface GalleryRepository {

    suspend fun getHotGallery() : Gallery

    suspend fun getTopGallery(): Gallery

    suspend fun getMyGallery(): Gallery
}