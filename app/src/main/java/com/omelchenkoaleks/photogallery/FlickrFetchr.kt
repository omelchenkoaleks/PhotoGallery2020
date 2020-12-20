package com.omelchenkoaleks.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omelchenkoaleks.photogallery.api.FlickrApi
import com.omelchenkoaleks.photogallery.api.FlickrResponse
import com.omelchenkoaleks.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Простой репозиторий, который инкапсулирует логику доступа к данным.
 * Определяет как получать и хранить набор данных.
 * Код пользовательского интерфейса будет запрашивать все данные из репозитория, потому что
 * ему неважно, как данные хранятся или извлекаются на самом деле.
 */

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }


    /**
     * После успешного завершения результат становится публичным путем установки значения responseLiveData.value.
     * Теперь, другие компоненты могут налюдать объект LiveData, чтобы получить результаты запроса.
     */
    fun fetchPhotos(): LiveData<List<GalleryItem>> {

        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos()

        flickrRequest.enqueue(object : Callback<FlickrResponse> {

            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                Log.d(TAG, "Response received")
                val flickrResponse: FlickrResponse? = response.body()
                val photoResponse: PhotoResponse? = flickrResponse?.photos
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems ?: mutableListOf()

                /**
                 * Отфильтровываем элементы галереи с пустыми значениями URL-адреса.
                 */
                galleryItems = galleryItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }
        })

        return responseLiveData
    }

}