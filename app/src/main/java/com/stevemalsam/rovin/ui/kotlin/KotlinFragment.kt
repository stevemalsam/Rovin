package com.stevemalsam.rovin.ui.kotlin

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.stevemalsam.rovin.R
import com.stevemalsam.rovin.databinding.FragmentKotlinBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.launch

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
        val progressBar = binding.progressBar
        recyclerView.adapter = photosRecyclerAdapter
        recyclerView.addItemDecoration(MarginItemDecoration(16))

//        kotlinViewModel.uiState.observe(viewLifecycleOwner) {
//            when(it) {
//                UIState.Loading -> progressBar.visibility = View.VISIBLE
//                is UIState.Success-> {
//                    progressBar.visibility = View.GONE
//                    photosRecyclerAdapter.dataSet = it.photos
//                    photosRecyclerAdapter.notifyDataSetChanged()
//                }
//                UIState.Error -> {
//                    progressBar.visibility = View.GONE
//                    Snackbar.make(root, getString(R.string.network_error), Snackbar.LENGTH_LONG)
//                        .setAction(getString(R.string.retry_question)) {
//                            kotlinViewModel.getCuriosityPhotos(1000, 2)
//                        }
//                        .show()
//                }
//            }
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                kotlinViewModel.uiState.collect {
                    when (it) {
                        UIState.Loading -> progressBar.visibility = View.VISIBLE
                        is UIState.Success -> {
                            progressBar.visibility = View.GONE
                            photosRecyclerAdapter.dataSet = it.photos
                            photosRecyclerAdapter.notifyDataSetChanged()
                        }

                        UIState.Error -> {
                            progressBar.visibility = View.GONE
                            Snackbar.make(
                                root,
                                getString(R.string.network_error),
                                Snackbar.LENGTH_LONG
                            )
                                .setAction(getString(R.string.retry_question)) {
                                    kotlinViewModel.getCuriosityPhotos(1000, 2)
                                }
                                .show()
                        }
                    }
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}