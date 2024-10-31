package com.stevemalsam.rovin.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stevemalsam.rovin.databinding.FragmentComposeBinding
import com.stevemalsam.rovin.ui.kotlin.KotlinViewModel
import kotlinx.coroutines.flow.collect

class ComposeFragment : Fragment() {

    private var _binding: FragmentComposeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val kotlinViewModel: KotlinViewModel by activityViewModels()

        _binding = FragmentComposeBinding.inflate(inflater, container, false)
        val view = binding.root
        val composeView = binding.composeView

        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ComposeScreen(kotlinViewModel, {
                        kotlinViewModel.getCuriosityPhotos(1000, 2)
                    })
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}