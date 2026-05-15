package com.example.kashtakala.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kashtakala.database.QuoteEntity
import com.example.kashtakala.databinding.FragmentQuotationBinding
import com.example.kashtakala.viewmodels.MainViewModel
import java.util.Locale

class QuotationFragment : Fragment() {

    private var _binding: FragmentQuotationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEstimation()

        binding.btnGenerateQuote.setOnClickListener {
            generateQuote()
        }

        binding.btnSaveQuote.setOnClickListener {
            saveQuote()
        }
        
        // Add a long click or a separate button if we had one in XML to share.
        // For now, let's trigger share after saving.
    }

    private fun observeEstimation() {
        // Pre-fill material cost if an estimate was just made in the Estimator fragment
        viewModel.currentMaterialEstimate.observe(viewLifecycleOwner) { estimate ->
            if (estimate > 0) {
                binding.etMaterialCost.setText(String.format(Locale.getDefault(), "%.0f", estimate))
            }
        }
    }

    private fun generateQuote() {
        val material = binding.etMaterialCost.text.toString().toDoubleOrNull() ?: 0.0
        val labor = binding.etLaborCost.text.toString().toDoubleOrNull() ?: 0.0
        val finishing = binding.etFinishingCost.text.toString().toDoubleOrNull() ?: 0.0
        val transp = binding.etTranspCost.text.toString().toDoubleOrNull() ?: 0.0

        val total = material + labor + finishing + transp

        binding.tvTotalCost.text = String.format(Locale.getDefault(), "₹%,.0f", total)
        binding.cardQuoteResult.visibility = View.VISIBLE
    }

    private fun saveQuote() {
        val name = binding.etCustomerName.text.toString()
        val material = binding.etMaterialCost.text.toString().toDoubleOrNull() ?: 0.0
        val labor = binding.etLaborCost.text.toString().toDoubleOrNull() ?: 0.0
        val finishing = binding.etFinishingCost.text.toString().toDoubleOrNull() ?: 0.0
        val transp = binding.etTranspCost.text.toString().toDoubleOrNull() ?: 0.0
        val total = material + labor + finishing + transp

        if (name.isNotEmpty()) {
            val quote = QuoteEntity(
                customerName = name,
                materialCost = material,
                laborCost = labor,
                finishingCost = finishing,
                transportationCost = transp,
                totalCost = total
            )
            viewModel.insertQuote(quote)
            
            Toast.makeText(context, "Quotation Saved Successfully", Toast.LENGTH_SHORT).show()
            shareQuotation(name, total)
        } else {
            Toast.makeText(context, "Please enter customer name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareQuotation(customerName: String, total: Double) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val message = """
            *Kashta-Kala Quotation*
            Customer: $customerName
            
            Material Cost: ₹${binding.etMaterialCost.text}
            Labor Cost: ₹${binding.etLaborCost.text}
            Finishing Cost: ₹${binding.etFinishingCost.text}
            ---
            *Total Estimated Cost: ₹%,.0f*
            
            _Generated via Kashta-Kala App_
        """.trimIndent().format(total)
        
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(shareIntent, "Share Quotation via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
