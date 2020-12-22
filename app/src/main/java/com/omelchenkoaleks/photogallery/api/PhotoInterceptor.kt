package com.omelchenkoaleks.photogallery.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY = "e3d73184b641b98c6b31cb2e5aafa202"

class PhotoInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        /**
         * chain.request() - для доступа к исходному запросу.
         */
        val originalRequest: Request = chain.request()

        /**
         * originalRequest.url() - извлекает исходный URL из запроса.
         * Добавляем с помощью newBuilder() параметры запроса.
         */
        val newUrl: HttpUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("extras", "url_s")
            .addQueryParameter("safesearch", "1")
            .build()

        /**
         * Создаем новый запрос на основе оригинального запроса и заменяем исходный URL на новый.
         */
        val newRequest: Request = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}