package io.keepcoding.network

import com.keepcoding.instagramparapobres.network.NetworkGallery

data class NetworkAlbum(
    val data: List<NetworkGallery.NetworkImage>,
    val success: Boolean,
    val status: Int
)
