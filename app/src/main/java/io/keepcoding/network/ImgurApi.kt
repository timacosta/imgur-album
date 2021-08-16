package io.keepcoding.network

import com.keepcoding.instagramparapobres.network.NetworkGallery
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImgurApi {

    @Headers("Authorization: Client-ID af0a278652e7cce")
    @GET("gallery/hot")
    suspend fun getHotGallery(): NetworkGallery

    @Headers("Authorization: Client-ID af0a278652e7cce")
    @GET("gallery/top")
    suspend fun getTopGallery(): NetworkGallery

    @GET("account/me/images")
    suspend fun getMyGallery(): NetworkGallery

    @GET("")
    suspend fun getAlbum(): NetworkGallery

}