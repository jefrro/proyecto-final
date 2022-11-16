package com.jesusfranco.proyecto.allmygames.ui.ui.completados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jesusfranco.proyecto.allmygames.databinding.FragmentCompletadosBinding

class CompletadosFragment : Fragment() {

    private var _binding: FragmentCompletadosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val completadosViewModel =
            ViewModelProvider(this).get(CompletadosViewModel::class.java)

        _binding = FragmentCompletadosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        completadosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}