package com.example.kashtakala.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kashtakala.R
import com.example.kashtakala.databinding.ItemDesignBinding
import com.example.kashtakala.models.FurnitureDesign
import java.util.Locale

class DesignAdapter(
    private val onItemClick: (FurnitureDesign) -> Unit,
    private val onFavoriteClick: (FurnitureDesign) -> Unit,
    private val onShareClick: (FurnitureDesign) -> Unit,
    private val onAddToCartClick: (FurnitureDesign) -> Unit,
    private val onBuyNowClick: (FurnitureDesign) -> Unit,
    private val isFavorite: (Int) -> Boolean = { false }
) : ListAdapter<FurnitureDesign, DesignAdapter.DesignViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignViewHolder {
        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DesignViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class DesignViewHolder(private val binding: ItemDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(design: FurnitureDesign) {
            binding.apply {
                designName.text = design.title
                priceText.text = String.format(Locale.getDefault(), "₹%,.0f", design.estimatedPrice)
                designInfo.text = design.dimensions
                categoryTag.text = design.category
                
                val isFav = isFavorite(design.id)
                val favoriteIcon = if (isFav) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_favorite_border
                }
                favoriteButton.setImageResource(favoriteIcon)
                
                if (isFav) {
                    favoriteButton.imageTintList = null 
                } else {
                    favoriteButton.imageTintList = ContextCompat.getColorStateList(root.context, R.color.primary_gold)
                }
                
                Glide.with(root.context)
                    .load(design.imageUrl)
                    .placeholder(R.drawable.ic_catalog)
                    .into(designImage)

                root.setOnClickListener { onItemClick(design) }
                favoriteButton.setOnClickListener { onFavoriteClick(design) }
                shareButton.setOnClickListener { onShareClick(design) }
                btnAddToCart.setOnClickListener { onAddToCartClick(design) }
                btnBuyNow.setOnClickListener { onBuyNowClick(design) }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FurnitureDesign>() {
        override fun areItemsTheSame(oldItem: FurnitureDesign, newItem: FurnitureDesign): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FurnitureDesign, newItem: FurnitureDesign): Boolean {
            return oldItem == newItem
        }
    }
}
