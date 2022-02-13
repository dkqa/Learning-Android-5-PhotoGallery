package com.dkqa.android.photogallery.paging

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PagingHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
    val bindTitle: (CharSequence) -> Unit = itemTextView::setText
}