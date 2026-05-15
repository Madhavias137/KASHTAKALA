package com.example.kashtakala.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kashtakala.adapters.BookingAdapter
import com.example.kashtakala.databinding.DialogAddBookingBinding
import com.example.kashtakala.databinding.FragmentBookingBinding
import com.example.kashtakala.models.BookingItem
import com.example.kashtakala.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.fabAddBooking.setOnClickListener {
            showAddBookingDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = BookingAdapter(
            onUpdateStatus = { booking ->
                showUpdateStatusDialog(booking)
            },
            onDelete = { booking ->
                viewModel.deleteBooking(booking)
                Toast.makeText(context, "Booking deleted", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvBookings.layoutManager = LinearLayoutManager(context)
        binding.rvBookings.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.allBookings.observe(viewLifecycleOwner) { bookings ->
            adapter.submitList(bookings)
        }
    }

    private fun showAddBookingDialog() {
        val dialogBinding = DialogAddBookingBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        dialogBinding.etBookingDate.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, day ->
                calendar.set(year, month, day)
                dialogBinding.etBookingDate.setText(dateFormat.format(calendar.time))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        dialogBinding.etDeliveryDate.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, day ->
                calendar.set(year, month, day)
                dialogBinding.etDeliveryDate.setText(dateFormat.format(calendar.time))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        dialogBinding.btnSaveBooking.setOnClickListener {
            val name = dialogBinding.etCustomerName.text.toString()
            val type = dialogBinding.etFurnitureType.text.toString()
            val date = dialogBinding.etBookingDate.text.toString()
            val delivery = dialogBinding.etDeliveryDate.text.toString()
            val amount = dialogBinding.etAmount.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotEmpty() && type.isNotEmpty()) {
                val newBooking = BookingItem(
                    customerName = name,
                    furnitureType = type,
                    bookingDate = date,
                    estimatedDelivery = delivery,
                    amount = amount,
                    status = "Pending"
                )
                viewModel.addBooking(newBooking)
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Fill required fields", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun showUpdateStatusDialog(booking: BookingItem) {
        val statuses = arrayOf("Pending", "In Progress", "Completed")
        AlertDialog.Builder(requireContext())
            .setTitle("Update Status")
            .setItems(statuses) { _, which ->
                val updatedBooking = booking.copy(status = statuses[which])
                viewModel.updateBooking(updatedBooking)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
