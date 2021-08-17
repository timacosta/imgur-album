package io.keepcoding.gallery

data class Album(
    val id: String,
    val title: String,
    val images: List<AlbumImage>
)

data class AlbumImage(
    val id: String,
    val link: String
)