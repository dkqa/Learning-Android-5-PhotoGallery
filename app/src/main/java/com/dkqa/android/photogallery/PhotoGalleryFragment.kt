package com.dkqa.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dkqa.android.photogallery.api.GalleryItem
import com.dkqa.android.photogallery.paging.PagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var adapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val flickrLiveData: LiveData<List<GalleryItem>> = FlickrFetchr().fetchPhotos()
//        flickrLiveData.observe(
//            this,
//            Observer { galleryItems ->
//                Log.d(TAG, "Response received: $galleryItems")
//            }
//        )
        photoGalleryViewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)
        adapter = PagingAdapter()

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
        photoRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        photoGalleryViewModel.galleryItemLiveData.observe(
//            viewLifecycleOwner,
//            Observer { galleryItems ->
////                Log.d(TAG, "Have gallery items from ViewModel $galleryItems")
//                photoRecyclerView.adapter = PhotoAdapter(galleryItems)
//            }
//        )
//        photoGalleryViewModel.myLiveData.observe(
//            viewLifecycleOwner,
//            Observer { galleryItems ->
////                Log.d(TAG, "Have gallery items from ViewModel $galleryItems")
//
//                photoRecyclerView.adapter = PhotoAdapter(galleryItems)
//            }
//        )
        lifecycleScope.launch{

            photoGalleryViewModel.flow.collectLatest {
                adapter.submitData(it)
                photoRecyclerView.adapter = adapter

            }
        }
    }

    private class PhotoHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class PhotoAdapter(private val galleryItems: List<GalleryItem>) : RecyclerView.Adapter<PhotoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView = TextView(parent.context)
            return PhotoHolder(textView)
        }

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindTitle(galleryItem.title)
        }

        override fun getItemCount(): Int {
            return galleryItems.size
        }

    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}