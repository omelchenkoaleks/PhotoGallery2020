package com.omelchenkoaleks.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel : ViewModel() {

    /**
     * Свойство для хранения объекта "живых" данных -
     * содержит список элементов галереи.
     */
    val galleryItemLiveData: LiveData<List<GalleryItem>> = FlickrFetchr().fetchPhotos()

}