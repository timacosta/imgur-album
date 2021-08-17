package io.keepcoding.network

data class NetworkAlbum(
    val data: NetworkAlbumDataItem,
    val success: Boolean,
    val status: Int
) {
    data class NetworkAlbumDataItem (
        val id: String,
        val title: String,
        val images: List<NetworkAlbumImage>
    )

    data class NetworkAlbumImage(
        val id: String,
        val title: String?,
        val description: String?,
        val type: String,
        val link: String
    )


}
