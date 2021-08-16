package io.keepcoding.album

import com.keepcoding.instagramparapobres.network.NetworkGallery
import io.keepcoding.gallery.Gallery
import io.keepcoding.gallery.Image
import io.keepcoding.network.ImgurApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class AlbumRepositoryImpl(private val imgurApi: ImgurApi): AlbumRepository {

    override suspend fun getAlbum(albumHash: String) =
        withContext(Dispatchers.IO) {
                imgurApi.getAlbum(albumHash).toDomain()
        }

    private fun NetworkGallery.toDomain(): Album {
        val images = data.filter { image ->
            val imageLink = image.images?.first()?.link ?: image.link
            imageLink.contains(".jpg") || imageLink.contains(".png")
        }.mapNotNull { image ->
            val imageLink = image.images?.first()?.link ?: image.link
            Image(
                id = image.id,
                title = image.title,
                url = imageLink,
                likes = image.favorite_count ?: 0,
                datetime = image.datetime,
                author = image.account_url,
            )
        }
        return Album(images)
    }

}
