package com.example.kashtakala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kashtakala.databinding.ItemSalesBinding
import com.example.kashtakala.models.SalesItem
import java.util.Locale

class SalesAdapter(
    private val onDelete: (SalesItem) -> Unit
) : ListAdapter<SalesItem, SalesAdapter.SalesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val binding = ItemSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SalesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SalesViewHolder(private val binding: ItemSalesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SalesItem) {
            binding.apply {
                tvItemName.text = item.itemName
                tvCustomerName.text = item.customerName
                tvSaleDate.text = item.saleDate
                tvPrice.text = String.format(Locale.getDefault(), "₹%,.0f", item.price)
                tvPaymentMethod.text = item.paymentMethod

                root.setOnLongClickListener {
                    onDelete(item)
                    true
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SalesItem>() {
        override fun areItemsTheSame(oldItem: SalesItem, newItem: SalesItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SalesItem, newItem: SalesItem): Boolean = oldItem == newItem
    }
}
