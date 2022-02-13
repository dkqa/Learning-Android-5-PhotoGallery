package com.dkqa.android.photogallery.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.RuntimeException

private const val TAG = "PagingSource"

class PagingSource(private val galleryPhotos: PagingGalleryPhotos) : PagingSource<Int, PagingPhotoItem>() {
    override fun getRefreshKey(state: PagingState<Int, PagingPhotoItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingPhotoItem> {
        val items = galleryPhotos.listPhotos

        if (items.isEmpty()) {
            return LoadResult.Error(RuntimeException("No items"))
        }

        val page = params.key ?: 1
        val pageSize = params.loadSize
        Log.d(TAG, "Load Page $page")

        val startPoint = page * pageSize - pageSize
        val endPoint = if ((page * pageSize) >= items.size) items.size - 1 else page * pageSize

        val currentItems = items.subList(startPoint, endPoint)
        val nextKey = if (currentItems.size < pageSize) null else page + 1
        val prevKey = if (page == 1) null else page - 1

        return LoadResult.Page(currentItems, prevKey, nextKey)
    }
}