package com.example.wirda_go.About

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wirda_go.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    // DATA LISTVIEW
    private val infoItems = listOf(

        InfoModel(
            "Tentang Suara Rakyat",
            "Platform pengaduan warga desa",
            "https://avatar.iran.liara.run/public/1"
        ),

        InfoModel(
            "Kebijakan Privasi",
            "Data pengaduan dijaga kerahasiaannya",
            "https://avatar.iran.liara.run/public/2"
        ),

        InfoModel(
            "Panduan Penggunaan",
            "Cara mengajukan dan memantau pengaduan",
            "https://avatar.iran.liara.run/public/3"
        ),

        InfoModel(
            "Versi Aplikasi",
            "v1.0.0 — Dikembangkan 2025",
            "https://avatar.iran.liara.run/public/4"
        ),

        InfoModel(
            "Pengembang",
            "Wirda Go Mobile App",
            "https://avatar.iran.liara.run/public/5"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar title
        binding.toolbar.title = "About"

        // SETUP LISTVIEW
        val adapter = InfoAdapter(requireContext(), infoItems)

        binding.listViewInfo.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}