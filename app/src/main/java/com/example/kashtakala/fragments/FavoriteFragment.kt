package com.example.kashtakala.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kashtakala.R
import com.example.kashtakala.adapters.DesignAdapter
import com.example.kashtakala.databinding.FragmentFavoriteBinding
import com.example.kashtakala.models.FurnitureDesign
import com.example.kashtakala.viewmodels.MainViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: DesignAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = DesignAdapter(
            onItemClick = { design ->
                val bundle = Bundle().apply {
                    putInt("designId", design.id)
                }
                findNavController().navigate(R.id.detailFragment, bundle)
            },
            onFavoriteClick = { design ->
                viewModel.toggleFavorite(design)
            },
            onShareClick = { design ->
                shareDesign(design)
            },
            onEstimateClick = { design ->
                val bundle = Bundle().apply {
                    putFloat("length", design.length.toFloat())
                    putFloat("width", design.width.toFloat())
                    putFloat("height", design.height.toFloat())
                }
                findNavController().navigate(R.id.estimatorFragment, bundle)
            },
            isFavorite = { true } // All items in this fragment are favorites
        )
        binding.rvFavorites.layoutManager = GridLayoutManager(context, 2)
        binding.rvFavorites.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.loadDummyDesigns()
        viewModel.allFavorites.observe(viewLifecycleOwner) { favorites ->
            viewModel.furnitureDesigns.observe(viewLifecycleOwner) { allDesigns ->
                val favIds = favorites.map { it.designId }.toSet()
                val favDesigns = allDesigns.filter { it.id in favIds }
                
                adapter.submitList(favDesigns)
                binding.tvNoFavorites.visibility = if (favDesigns.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun shareDesign(design: FurnitureDesign) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this ${design.title} on Kashta-Kala!\nPrice: ₹${design.estimatedPrice}")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Design"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
