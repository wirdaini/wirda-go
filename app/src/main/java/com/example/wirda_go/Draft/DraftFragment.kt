package com.example.wirda_go.Draft

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wirda_go.data.AppDatabase
import com.example.wirda_go.data.entity.DraftAspirasiEntity
import com.example.wirda_go.databinding.FragmentDraftBinding
import kotlinx.coroutines.launch

class DraftFragment : Fragment() {

    private var _binding: FragmentDraftBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())
        binding.rvDraft.layoutManager = LinearLayoutManager(requireContext())
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(requireContext(), DraftFormActivity::class.java))
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.draftAspirasiDao().getAll()
            binding.rvDraft.adapter = DraftAdapter(data) { draft ->
                lifecycleScope.launch {
                    db.draftAspirasiDao().delete(draft)
                    loadData()
                }
            }
            binding.rvDraft.isVisible = data.isNotEmpty()
            binding.tvEmpty.isVisible = data.isEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}