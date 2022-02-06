package com.dkqa.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=c9eed8ebeb8d09e442d5cb79259488db" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<PhotoResponse>
}