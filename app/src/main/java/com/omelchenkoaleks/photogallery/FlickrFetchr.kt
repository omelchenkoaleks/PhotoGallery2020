package com.omelchenkoaleks.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omelchenkoaleks.photogallery.api.FlickrApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
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
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    /**
     * После успешного завершения результат становится публичным путем установки значения responseLiveData.value.
     * Теперь, другие компоненты могут налюдать объект LiveData, чтобы получить результаты запроса.
     */
    fun fetchContents(): LiveData<String> {

        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest: Call<String> = flickrApi.fetchContents()

        flickrRequest.enqueue(object : Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }
        })

        return responseLiveData
    }

}