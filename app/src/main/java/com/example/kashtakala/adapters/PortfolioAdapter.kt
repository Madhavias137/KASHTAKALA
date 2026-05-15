package com.example.kashtakala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kashtakala.R
import com.example.kashtakala.databinding.ItemPortfolioBinding
import com.example.kashtakala.models.PortfolioItem

class PortfolioAdapter(
    private val onDeleteClick: (PortfolioItem) -> Unit
) : ListAdapter<PortfolioItem, PortfolioAdapter.PortfolioViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding = ItemPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PortfolioViewHolder(private val binding: ItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PortfolioItem) {
            binding.apply {
                tvTitle.text = item.title
                tvDescription.text = item.description
                
                Glide.with(root.context)
                    .load(item.imageUri)
                    .placeholder(R.drawable.ic_portfolio)
                    .into(ivProject)

                btnDelete.setOnClickListener { onDeleteClick(item) }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PortfolioItem>() {
        override fun areItemsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
            return oldItem == newItem
        }
    }
}
