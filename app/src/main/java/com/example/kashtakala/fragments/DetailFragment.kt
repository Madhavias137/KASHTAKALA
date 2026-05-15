package com.example.kashtakala.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kashtakala.R
import com.example.kashtakala.databinding.FragmentDetailBinding
import com.example.kashtakala.viewmodels.MainViewModel
import java.util.Locale

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val designId = arguments?.getInt("designId") ?: -1
        val design = viewModel.getDesignById(designId)

        if (design != null) {
            binding.apply {
                tvTitle.text = design.title
                tvPrice.text = String.format(Locale.getDefault(), "₹%,.0f", design.estimatedPrice)
                tvDescription.text = design.description
                tvDimensions.text = design.dimensions
                tvMaterial.text = design.materialType

                Glide.with(this@DetailFragment)
                    .load(design.imageUrl)
                    .placeholder(R.drawable.ic_catalog)
                    .into(ivProduct)

                btnFavorite.setOnClickListener {
                    viewModel.toggleFavorite(design)
                }

                btnShare.setOnClickListener {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, """
                            Check out this design on Kashta-Kala:
                            Product: ${design.title}
                            Dimensions: ${design.dimensions}
                            Estimated Price: ₹${String.format(Locale.getDefault(), "%,.0f", design.estimatedPrice)}
                            
                            Download Kashta-Kala to browse more!
                        """.trimIndent())
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(shareIntent, "Share Design"))
                }

                btnVisualizer.setOnClickListener {
                    val bundle = Bundle().apply {
                        putInt("designId", design.id)
                    }
                    findNavController().navigate(R.id.action_detail_to_visualizer, bundle)
                }

                btnAddToCart.setOnClickListener {
                    Toast.makeText(context, "${design.title} added to cart", Toast.LENGTH_SHORT).show()
                }

                btnBuyNow.setOnClickListener {
                    Toast.makeText(context, "Proceeding to buy ${design.title}", Toast.LENGTH_SHORT).show()
                }

                viewModel.allFavorites.observe(viewLifecycleOwner) { favorites ->
                    val isFav = favorites.any { it.designId == design.id }
                    btnFavorite.setImageResource(
                        if (isFav) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                    )
                    
                    if (isFav) {
                        btnFavorite.imageTintList = null
                    } else {
                        btnFavorite.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.primary_gold)
                    }
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
