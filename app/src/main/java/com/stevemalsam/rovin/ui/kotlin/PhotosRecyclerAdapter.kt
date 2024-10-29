package com.stevemalsam.rovin.ui.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stevemalsam.rovin.databinding.PhotoCardBinding
import com.stevemalsam.rovin.models.Photo

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
                binding.photoTitle.text = "$id"
            }
        }
    }
}