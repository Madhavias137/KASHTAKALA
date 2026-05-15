package com.example.kashtakala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kashtakala.R
import com.example.kashtakala.databinding.ItemBookingBinding
import com.example.kashtakala.models.BookingItem
import java.util.Locale

class BookingAdapter(
    private val onUpdateStatus: (BookingItem) -> Unit,
    private val onDelete: (BookingItem) -> Unit
) : ListAdapter<BookingItem, BookingAdapter.BookingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding = ItemBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookingViewHolder(private val binding: ItemBookingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookingItem) {
            binding.apply {
                tvCustomerName.text = item.customerName
                tvFurnitureType.text = item.furnitureType
                tvBookingDate.text = "Booked: ${item.bookingDate}"
                tvDeliveryDate.text = "Delivery: ${item.estimatedDelivery}"
                tvAmount.text = String.format(Locale.getDefault(), "₹%,.0f", item.amount)
                tvStatus.text = item.status

                val statusColor = when (item.status) {
                    "Completed" -> ContextCompat.getColor(root.context, android.R.color.holo_green_dark)
                    "In Progress" -> ContextCompat.getColor(root.context, R.color.primary_gold)
                    else -> ContextCompat.getColor(root.context, R.color.grey_800)
                }
                tvStatus.setTextColor(statusColor)

                root.setOnClickListener { onUpdateStatus(item) }
                root.setOnLongClickListener {
                    onDelete(item)
                    true
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookingItem>() {
        override fun areItemsTheSame(oldItem: BookingItem, newItem: BookingItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: BookingItem, newItem: BookingItem): Boolean = oldItem == newItem
    }
}
