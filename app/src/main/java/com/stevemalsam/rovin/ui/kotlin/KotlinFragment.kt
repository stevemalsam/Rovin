package com.stevemalsam.rovin.ui.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stevemalsam.rovin.databinding.FragmentKotlinBinding

class KotlinFragment : Fragment() {

    private var _binding: FragmentKotlinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val kotlinViewModel =
            ViewModelProvider(this).get(KotlinViewModel::class.java)

        _binding = FragmentKotlinBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textKotlin
        kotlinViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}