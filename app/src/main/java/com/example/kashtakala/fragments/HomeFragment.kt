package com.example.kashtakala.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kashtakala.R
import com.example.kashtakala.adapters.CategoryAdapter
import com.example.kashtakala.adapters.DesignAdapter
import com.example.kashtakala.databinding.FragmentHomeBinding
import com.example.kashtakala.models.Category
import com.example.kashtakala.models.FurnitureDesign
import com.example.kashtakala.viewmodels.MainViewModel
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    
    private var allDesigns: List<FurnitureDesign> = emptyList()
    private var favoriteIds: Set<Int> = emptySet()

    private val voiceRecognitionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0) ?: ""
            if (spokenText.isNotEmpty()) {
                binding.etSearch.setText(spokenText)
                filterDesigns(spokenText)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoriesRecyclerView()
        setupSectionedRecyclerViews()
        setupClickListeners()
        setupSearch()
        observeViewModel()
        
        viewModel.loadDummyDesigns()

        binding.tvUserName.text = "Master Artisan"
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterDesigns(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterDesigns(query: String) {
        if (query.isEmpty()) {
            binding.layoutTeak.visibility = View.VISIBLE
            binding.layoutSofas.visibility = View.VISIBLE
            binding.layoutBeds.visibility = View.VISIBLE
            binding.layoutWoodenModern.visibility = View.VISIBLE
            binding.layoutTrending.visibility = View.VISIBLE
            
            updateLists(allDesigns)
            return
        }

        val filteredTeak = allDesigns.filter { 
            it.materialType.contains("Teak", ignoreCase = true) && 
            (it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true))
        }

        val filteredSofas = allDesigns.filter { 
            it.category.contains("Sofa", ignoreCase = true) && 
            (it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true))
        }
        
        val filteredBeds = allDesigns.filter { 
            it.category.contains("Bed", ignoreCase = true) && 
            (it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true))
        }

        val filteredWooden = allDesigns.filter { 
            it.category.contains("Wooden Modern", ignoreCase = true) && 
            (it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true))
        }

        val filteredTrending = allDesigns.filter { 
            !it.materialType.contains("Teak", ignoreCase = true) && 
            !it.category.contains("Sofa", ignoreCase = true) && 
            !it.category.contains("Bed", ignoreCase = true) &&
            !it.category.contains("Wooden Modern", ignoreCase = true) &&
            (it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true))
        }

        binding.layoutTeak.visibility = if (filteredTeak.isNotEmpty()) View.VISIBLE else View.GONE
        binding.layoutSofas.visibility = if (filteredSofas.isNotEmpty()) View.VISIBLE else View.GONE
        binding.layoutBeds.visibility = if (filteredBeds.isNotEmpty()) View.VISIBLE else View.GONE
        binding.layoutWoodenModern.visibility = if (filteredWooden.isNotEmpty()) View.VISIBLE else View.GONE
        binding.layoutTrending.visibility = if (filteredTrending.isNotEmpty()) View.VISIBLE else View.GONE

        (binding.rvTeak.adapter as? DesignAdapter)?.submitList(filteredTeak)
        (binding.rvSofas.adapter as? DesignAdapter)?.submitList(filteredSofas)
        (binding.rvBeds.adapter as? DesignAdapter)?.submitList(filteredBeds)
        (binding.rvWoodenModern.adapter as? DesignAdapter)?.submitList(filteredWooden)
        (binding.rvTrending.adapter as? DesignAdapter)?.submitList(filteredTrending)
    }

    private fun updateLists(designs: List<FurnitureDesign>) {
        val teak = designs.filter { it.materialType.contains("Teak", ignoreCase = true) }
        val sofas = designs.filter { it.category.contains("Sofa", ignoreCase = true) }
        val beds = designs.filter { it.category.contains("Bed", ignoreCase = true) }
        val wooden = designs.filter { it.category.contains("Wooden Modern", ignoreCase = true) }
        val others = designs.filter { 
            !it.materialType.contains("Teak", ignoreCase = true) && 
            !it.category.contains("Sofa", ignoreCase = true) && 
            !it.category.contains("Bed", ignoreCase = true) &&
            !it.category.contains("Wooden Modern", ignoreCase = true)
        }

        (binding.rvTeak.adapter as? DesignAdapter)?.submitList(teak)
        (binding.rvSofas.adapter as? DesignAdapter)?.submitList(sofas)
        (binding.rvBeds.adapter as? DesignAdapter)?.submitList(beds)
        (binding.rvWoodenModern.adapter as? DesignAdapter)?.submitList(wooden)
        (binding.rvTrending.adapter as? DesignAdapter)?.submitList(others)
    }

    private fun setupClickListeners() {
        binding.ivUserProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        binding.ivGoToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_favorite)
        }

        binding.ivVoiceAssist.setOnClickListener {
            startVoiceRecognition()
        }
        
        binding.bannerCard.setOnClickListener {
            val bundle = Bundle().apply { putString("category", "Teak") }
            findNavController().navigate(R.id.catalogFragment, bundle)
        }

        binding.cardBookings.setOnClickListener {
            findNavController().navigate(R.id.bookingFragment)
        }

        binding.cardSales.setOnClickListener {
            findNavController().navigate(R.id.salesFragment)
        }
    }

    private fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Searching for furniture...")
        }
        try {
            voiceRecognitionLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Voice recognition not supported", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCategoriesRecyclerView() {
        val categories = listOf(
            Category("Sofas", android.R.drawable.ic_menu_gallery),
            Category("Beds", android.R.drawable.ic_menu_gallery),
            Category("Dining", android.R.drawable.ic_menu_gallery),
            Category("Cabinets", android.R.drawable.ic_menu_gallery),
            Category("Kitchen", android.R.drawable.ic_menu_gallery)
        )
        val adapter = CategoryAdapter(categories) { category ->
            val bundle = Bundle().apply { putString("category", category.name) }
            findNavController().navigate(R.id.catalogFragment, bundle)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = adapter
    }

    private fun setupSectionedRecyclerViews() {
        binding.rvTeak.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTeak.adapter = createDesignAdapter()

        binding.rvSofas.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSofas.adapter = createDesignAdapter()

        binding.rvBeds.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBeds.adapter = createDesignAdapter()

        binding.rvWoodenModern.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvWoodenModern.adapter = createDesignAdapter()

        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrending.adapter = createDesignAdapter()
    }

    private fun createDesignAdapter(): DesignAdapter {
        return DesignAdapter(
            onItemClick = { design ->
                val bundle = Bundle().apply {
                    putInt("designId", design.id)
                }
                findNavController().navigate(R.id.action_home_to_detail, bundle)
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
                findNavController().navigate(R.id.action_home_to_estimator, bundle)
            },
            isFavorite = { id -> favoriteIds.contains(id) }
        )
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
        viewModel.furnitureDesigns.observe(viewLifecycleOwner) { designs ->
            allDesigns = designs
            if (binding.etSearch.text.isNullOrEmpty()) {
                updateLists(designs)
            } else {
                filterDesigns(binding.etSearch.text.toString())
            }
        }
        
        viewModel.allFavorites.observe(viewLifecycleOwner) { favorites ->
            favoriteIds = favorites.map { it.designId }.toSet()
            binding.rvTeak.adapter?.notifyDataSetChanged()
            binding.rvSofas.adapter?.notifyDataSetChanged()
            binding.rvBeds.adapter?.notifyDataSetChanged()
            binding.rvWoodenModern.adapter?.notifyDataSetChanged()
            binding.rvTrending.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
