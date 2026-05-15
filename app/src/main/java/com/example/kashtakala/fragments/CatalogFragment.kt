package com.example.kashtakala.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kashtakala.R
import com.example.kashtakala.adapters.DesignAdapter
import com.example.kashtakala.databinding.FragmentCatalogBinding
import com.example.kashtakala.models.FurnitureDesign
import com.example.kashtakala.viewmodels.MainViewModel

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    
    private var favoriteIds: Set<Int> = emptySet()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val adapter = DesignAdapter(
            onItemClick = { design ->
                val bundle = Bundle().apply {
                    putInt("designId", design.id)
                }
                findNavController().navigate(R.id.action_catalog_to_detail, bundle)
            },
            onFavoriteClick = { design ->
                viewModel.toggleFavorite(design)
            },
            onShareClick = { design ->
                shareDesign(design)
            },
            onAddToCartClick = { design ->
                Toast.makeText(context, "${design.title} added to cart", Toast.LENGTH_SHORT).show()
            },
            onBuyNowClick = { design ->
                Toast.makeText(context, "Proceeding to buy ${design.title}", Toast.LENGTH_SHORT).show()
            },
            isFavorite = { id -> favoriteIds.contains(id) }
        )
        binding.rvCatalog.layoutManager = GridLayoutManager(context, 2)
        binding.rvCatalog.adapter = adapter
    }

    private fun shareDesign(design: FurnitureDesign) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this ${design.title} on Kashta-Kala!\nPrice: ₹${design.estimatedPrice}")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Design"))
    }

    private fun observeViewModel() {
        val categoryFilter = arguments?.getString("category") ?: "All"
        
        viewModel.furnitureDesigns.observe(viewLifecycleOwner) { designs ->
            val filteredList = if (categoryFilter == "All") {
                designs
            } else {
                designs.filter { it.category.contains(categoryFilter, ignoreCase = true) }
            }
            (binding.rvCatalog.adapter as? DesignAdapter)?.submitList(filteredList)
        }
        
        viewModel.allFavorites.observe(viewLifecycleOwner) { favorites ->
            favoriteIds = favorites.map { it.designId }.toSet()
            binding.rvCatalog.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
