package com.dkqa.android.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dkqa.android.photogallery.api.GalleryItem
import com.dkqa.android.photogallery.paging.PagingSource
import com.dkqa.android.photogallery.paging.PagingGalleryPhotos

class PhotoGalleryViewModel : ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>
//    val photoItemsLiveData: LiveData<List<PhotoItem>>

//    val myLiveData = Pager(PagingConfig(20),
//    pagingSourceFactory = { PagingSource(PagingGalleryPhotos()) }
//    ).liveData
    val flow = Pager(PagingConfig(20),
    pagingSourceFactory = { PagingSource(PagingGalleryPhotos()) }
    ).flow

    init {
        galleryItemLiveData = FlickrFetchr().fetchPhotos()
    }
}