package com.example.kashtakala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kashtakala.R
import com.example.kashtakala.databinding.ItemFurnitureBinding
import com.example.kashtakala.models.Furniture

class FurnitureAdapter(
    private var items: List<Furniture>,
    private val onItemClick: (Furniture) -> Unit,
    private val onFavoriteClick: (Furniture) -> Unit
) : RecyclerView.Adapter<FurnitureAdapter.FurnitureViewHolder>() {

    inner class FurnitureViewHolder(private val binding: ItemFurnitureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Furniture) {
            binding.apply {
                tvFurnitureName.text = item.name
                tvFurniturePrice.text = item.price
                
                Glide.with(root.context)
                    .load(item.imageUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(ivFurniture)

                btnFavorite.setImageResource(
                    if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )

                btnFavorite.setOnClickListener {
                    item.isFavorite = !item.isFavorite
                    notifyItemChanged(adapterPosition)
                    onFavoriteClick(item)
                }

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
        val binding = ItemFurnitureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FurnitureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Furniture>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
