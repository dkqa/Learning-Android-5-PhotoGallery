package com.dkqa.android.photogallery.paging


class PagingGalleryPhotos {
    val listPhotos: MutableList<PagingPhotoItem> = mutableListOf()
    init {
        listPhotos.apply {
            for (i in 1..200) {
                add(PagingPhotoItem(i.toString()))
            }
        }
    }
}