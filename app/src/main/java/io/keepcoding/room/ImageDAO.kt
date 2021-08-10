package io.keepcoding.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ImageDAO {

    @Query("SELECT * FROM images WHERE type = :imageType")
    suspend fun getImages(imageType: RoomImage.ImageType): List<RoomImage>



    @Insert(onConflict = REPLACE)
    suspend fun insertImages(imagesList: List<RoomImage>)
}