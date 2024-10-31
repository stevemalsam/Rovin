package com.stevemalsam.rovin.ui.kotlin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.memory.MemoryCache
import coil3.request.error
import coil3.request.placeholder
import com.stevemalsam.rovin.databinding.PhotoCardBinding
import com.stevemalsam.rovin.network.models.Photo

class PhotosRecyclerAdapter(var dataSet: List<Photo>) : RecyclerView.Adapter<PhotosRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: PhotoCardBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(dataSet[position]) {
                binding.photoTitle.text = "${id}"
                binding.photoEarthDate.text = earthDate.toString()
                binding.marsPhotoView.apply {
                    var placeholder: MemoryCache.Key? = null

                    load(imgSrc) {
                        placeholder(ColorDrawable(Color.BLUE))
                        error(ColorDrawable(Color.RED))
                        listener { _, result -> placeholder = result.memoryCacheKey}
                    }
                }
            }
        }
    }
}