package com.example.wirda_go.Kategori

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
import com.example.wirda_go.data.entity.KategoriPengaduanEntity
import com.example.wirda_go.databinding.FragmentKategoriBinding
import kotlinx.coroutines.launch

class KategoriFragment : Fragment() {

    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())
        binding.rvKategori.layoutManager = LinearLayoutManager(requireContext())
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(requireContext(), KategoriFormActivity::class.java))
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.kategoriPengaduanDao().getAll()
            binding.rvKategori.adapter = KategoriAdapter(data) { kategori ->
                lifecycleScope.launch {
                    db.kategoriPengaduanDao().delete(kategori)
                    loadData()
                }
            }
            binding.rvKategori.isVisible = data.isNotEmpty()
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