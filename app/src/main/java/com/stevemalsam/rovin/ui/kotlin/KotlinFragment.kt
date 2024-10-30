package com.stevemalsam.rovin.ui.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stevemalsam.rovin.databinding.FragmentKotlinBinding

class KotlinFragment : Fragment() {

    private var _binding: FragmentKotlinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val photosRecyclerAdapter: PhotosRecyclerAdapter = PhotosRecyclerAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val kotlinViewModel: KotlinViewModel by activityViewModels()

        _binding = FragmentKotlinBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView
        recyclerView.adapter = photosRecyclerAdapter

        kotlinViewModel.photos.observe(viewLifecycleOwner) {
            photosRecyclerAdapter.dataSet = it
            photosRecyclerAdapter.notifyDataSetChanged()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}