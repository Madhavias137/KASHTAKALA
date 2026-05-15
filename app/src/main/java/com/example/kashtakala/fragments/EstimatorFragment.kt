package com.example.kashtakala.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kashtakala.R
import com.example.kashtakala.databinding.FragmentEstimatorBinding
import com.example.kashtakala.viewmodels.MainViewModel
import java.util.Locale

class EstimatorFragment : Fragment() {

    private var _binding: FragmentEstimatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEstimatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill dimensions if passed from Catalog/Detail/Home
        // Using getFloat as defined in nav_graph.xml for better compatibility
        val length = arguments?.getFloat("length", 0.0f) ?: 0.0f
        val width = arguments?.getFloat("width", 0.0f) ?: 0.0f
        val height = arguments?.getFloat("height", 0.0f) ?: 0.0f

        if (length > 0) binding.etLength.setText(String.format(Locale.getDefault(), "%.1f", length))
        if (width > 0) binding.etWidth.setText(String.format(Locale.getDefault(), "%.1f", width))
        if (height > 0) binding.etHeight.setText(String.format(Locale.getDefault(), "%.1f", height))

        // If we have pre-filled data, calculate immediately
        if (length > 0 && width > 0 && height > 0) {
            calculateEstimation()
        }

        binding.btnCalculate.setOnClickListener {
            calculateEstimation()
        }

        binding.btnGoToQuote.setOnClickListener {
            findNavController().navigate(R.id.action_estimator_to_quotation)
        }
    }

    private fun calculateEstimation() {
        val lStr = binding.etLength.text.toString()
        val wStr = binding.etWidth.text.toString()
        val hStr = binding.etHeight.text.toString()

        if (lStr.isNotEmpty() && wStr.isNotEmpty() && hStr.isNotEmpty()) {
            val l = lStr.toDouble()
            val w = wStr.toDouble()
            val h = hStr.toDouble()

            // Calculate Square Feet (Surface Area - simplified for a rectangular block)
            // Surface Area = 2 * (lw + wh + hl)
            val sqFt = 2 * (l * w + w * h + h * l)
            
            // Assume a standard rate per sq ft for pre-filling the quote (e.g., ₹500/sqft for Teak)
            val estimatedCost = sqFt * 500.0

            binding.cardResults.visibility = View.VISIBLE
            binding.tvArea.text = String.format(Locale.getDefault(), "Total Surface: %.2f Sq. Ft.", sqFt)
            binding.tvVolume.text = String.format(Locale.getDefault(), "Volume: %.2f Cu. Ft.", (l * w * h))
            binding.tvRequirement.text = String.format(Locale.getDefault(), "Est. Material Cost: ₹%,.0f", estimatedCost)

            // Save to ViewModel so QuotationFragment can use it
            viewModel.currentMaterialEstimate.value = estimatedCost
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
