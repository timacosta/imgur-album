package io.keepcoding.gallery


import io.keepcoding.network.ImgurApi
import com.keepcoding.instagramparapobres.network.NetworkGallery
import io.keepcoding.room.ImageDAO
import io.keepcoding.room.RoomImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GalleryRepositoryImpl(
    private val imgurApi: ImgurApi,
    private val imageDAO: ImageDAO
) : GalleryRepository {

    override suspend fun getHotGallery() =
        withContext(Dispatchers.IO) {
            try {
                imgurApi.getHotGallery().toDomain().also { gallery ->
                    imageDAO.insertImages(gallery.toRoom(RoomImage.ImageType.HOT))
                }

            } catch (e: Exception) {
                Timber.e(e)
                imageDAO.getImages(RoomImage.ImageType.HOT).toDomain()
            }
        }

    override suspend fun getTopGallery() =
        withContext(Dispatchers.IO) {
            try {
                imgurApi.getTopGallery().toDomain().also { gallery ->
                    imageDAO.insertImages(gallery.toRoom(RoomImage.ImageType.TOP))
                }

            } catch (e: Exception) {
                Timber.e(e)
                imageDAO.getImages(RoomImage.ImageType.TOP).toDomain()
            }
        }

    override suspend fun getMyGallery() =
        withContext(Dispatchers.IO) { imgurApi.getMyGallery().toDomain() }

    private fun NetworkGallery.toDomain(): Gallery {
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

        return Gallery(images)
    }

    private fun List<RoomImage>.toDomain(): Gallery {
        return Gallery(map { roomImage ->
            Image(
                id = roomImage.id,
                title = roomImage.title,
                url = roomImage.url,
                likes = roomImage.likes,
                datetime = roomImage.datetime,
                author = roomImage.author,
            )
        })
    }

    private fun Gallery.toRoom(imageType: RoomImage.ImageType): List<RoomImage> =
        images.map { image ->
            RoomImage(
                id = image.id,
                title = image.title,
                url = image.url,
                likes = image.likes,
                datetime = image.datetime,
                author = image.author,
                imageType = imageType,
                isAlbum = false
            )
        }
}