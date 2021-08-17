package io.keepcoding.gallery


data class Gallery(val images: List<Image>)

data class Image(
    val id: String,
    val title: String?,
    val url: String,
    val likes: Int,
    val datetime: Long,
    val author: String?,
    val isAlbum: Boolean,
    val albumImagesCount: Int
) {
    val authorAvatar: String by lazy {
        "https://imgur.com/user/${author}/avatar"
    }
}

