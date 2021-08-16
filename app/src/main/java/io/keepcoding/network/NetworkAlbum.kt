package io.keepcoding.network

import com.keepcoding.instagramparapobres.network.NetworkGallery

data class NetworkAlbum(
    val data: NetworkAlbumData,
    val success: Boolean,
    val status: Int
) {
    data class NetworkAlbumData (
        val id: String,
        val title: String,
        val images: List<Image>
    )

    data class Image(
        val id: String,
        val title: String?,
        val description: String?,
        val type: String,
        val link: String
    )


}
