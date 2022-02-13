package com.dkqa.android.photogallery.paging

import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class PagingAdapter(): PagingDataAdapter<PagingPhotoItem, PagingHolder>(PhotoDiffUtil()) {

    override fun onBindViewHolder(holder: PagingHolder, position: Int) {
        val item = getItem(position)?.text ?: "Empty"
        holder.bindTitle(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingHolder {
        val textView = TextView(parent.context)
        return PagingHolder(textView)
    }

    private class PhotoDiffUtil: DiffUtil.ItemCallback<PagingPhotoItem>(){
        override fun areItemsTheSame(oldItem :PagingPhotoItem, newItem :PagingPhotoItem) :Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem :PagingPhotoItem, newItem :PagingPhotoItem) :Boolean {
            return oldItem == newItem
        }

    }
}