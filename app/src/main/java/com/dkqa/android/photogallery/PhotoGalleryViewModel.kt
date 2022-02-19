package com.dkqa.android.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dkqa.android.photogallery.api.GalleryItem

class PhotoGalleryViewModel : ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {
        galleryItemLiveData = FlickrFetchr().searchPhotos("bicycle")
    }
}