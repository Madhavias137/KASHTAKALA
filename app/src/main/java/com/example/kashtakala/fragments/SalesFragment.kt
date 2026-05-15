package com.example.kashtakala.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kashtakala.adapters.SalesAdapter
import com.example.kashtakala.databinding.DialogAddSaleBinding
import com.example.kashtakala.databinding.FragmentSalesBinding
import com.example.kashtakala.models.SalesItem
import com.example.kashtakala.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class SalesFragment : Fragment() {

    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: SalesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.fabAddSale.setOnClickListener {
            showAddSaleDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = SalesAdapter { sale ->
            viewModel.deleteSale(sale)
            Toast.makeText(context, "Sale record deleted", Toast.LENGTH_SHORT).show()
        }
        binding.rvSales.layoutManager = LinearLayoutManager(context)
        binding.rvSales.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.allSales.observe(viewLifecycleOwner) { sales ->
            adapter.submitList(sales)
        }
    }

    private fun showAddSaleDialog() {
        val dialogBinding = DialogAddSaleBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnSaveSale.setOnClickListener {
            val itemName = dialogBinding.etItemName.text.toString()
            val customerName = dialogBinding.etCustomerName.text.toString()
            val price = dialogBinding.etPrice.text.toString().toDoubleOrNull() ?: 0.0
            val paymentMethod = dialogBinding.etPaymentMethod.text.toString()
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            if (itemName.isNotEmpty() && customerName.isNotEmpty()) {
                val newSale = SalesItem(
                    itemName = itemName,
                    customerName = customerName,
                    saleDate = date,
                    price = price,
                    paymentMethod = paymentMethod
                )
                viewModel.addSale(newSale)
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
