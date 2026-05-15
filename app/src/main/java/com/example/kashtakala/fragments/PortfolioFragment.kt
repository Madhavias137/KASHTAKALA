package com.example.kashtakala.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kashtakala.adapters.PortfolioAdapter
import com.example.kashtakala.databinding.DialogAddPortfolioBinding
import com.example.kashtakala.databinding.FragmentPortfolioBinding
import com.example.kashtakala.models.PortfolioItem
import com.example.kashtakala.viewmodels.MainViewModel
import java.io.File
import java.io.FileOutputStream

class PortfolioFragment : Fragment() {

    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            showAddProjectDialog(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        binding.fabAddProject.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun showAddProjectDialog(imageUri: Uri) {
        val dialogBinding = DialogAddPortfolioBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.ivSelectedProject.setImageURI(imageUri)

        dialogBinding.btnSaveProject.setOnClickListener {
            val title = dialogBinding.etProjectTitle.text.toString()
            val description = dialogBinding.etProjectDescription.text.toString()

            if (title.isNotEmpty()) {
                val internalUri = saveImageToInternalStorage(imageUri)
                val newItem = PortfolioItem(
                    title = title,
                    description = description,
                    imageUri = internalUri?.toString() ?: imageUri.toString()
                )
                viewModel.addPortfolioItem(newItem)
                dialog.dismiss()
                Toast.makeText(context, "Project added to portfolio!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun saveImageToInternalStorage(uri: Uri): Uri? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, "portfolio_${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            Uri.fromFile(file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun setupRecyclerView() {
        val adapter = PortfolioAdapter(onDeleteClick = { item -> viewModel.deletePortfolioItem(item) })
        binding.rvPortfolio.layoutManager = LinearLayoutManager(context)
        binding.rvPortfolio.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.allPortfolioItems.observe(viewLifecycleOwner) { items ->
            (binding.rvPortfolio.adapter as? PortfolioAdapter)?.submitList(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
