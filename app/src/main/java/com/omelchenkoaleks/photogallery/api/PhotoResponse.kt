package com.omelchenkoaleks.photogallery.api

import com.google.gson.annotations.SerializedName
import com.omelchenkoaleks.photogallery.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}