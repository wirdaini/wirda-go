package com.example.wirda_go.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wirda_go.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // Data pengaduan bertema Suara Rakyat
    private val pengaduanList = listOf(
        PengaduanModel(
            "Ade Talia Laksmiwati",
            "Rumah Hancur akibat Banjir",
            "Infrastruktur Desa",
            "Menunggu",
            "https://images.unsplash.com/photo-1547683905-f686c993aae5"
        ),

        PengaduanModel(
            "Satya Susanti",
            "Jalan Berlubang di RT 05",
            "Infrastruktur Desa",
            "Diproses",
            "https://images.unsplash.com/photo-1500375592092-40eb2168fd21"
        ),

        PengaduanModel(
            "Caraka Puspita",
            "Sampah Menumpuk di Jalan Obor",
            "Lingkungan & Kebersihan",
            "Menunggu",
            "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b"
        ),

        PengaduanModel(
            "Hardi Usaha",
            "Jalan berlubang di Jl. Infrastruktur",
            "Infrastruktur Desa",
            "Diproses",
            "https://images.unsplash.com/photo-1521207418485-99c705420785"
        ),

        PengaduanModel(
            "Jefri Suartini",
            "Pengaduan trotoar rusak di Palu",
            "Infrastruktur Desa",
            "Selesai",
            "https://images.unsplash.com/photo-1480714378408-67cf0d13bc1b"
        ),

        PengaduanModel(
            "Endah Zukarnaen",
            "Pengaduan banjir di Kupang",
            "Lingkungan & Kebersihan",
            "Selesai",
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
        ),

        PengaduanModel(
            "Talia Prasetya",
            "Pengaduan fasilitas umum tidak terawat",
            "Infrastruktur Desa",
            "Diproses",
            "https://images.unsplash.com/photo-1518005020951-eccb494ad742"
        ),

        PengaduanModel(
            "Kunthara Megantara",
            "Pengaduan listrik sering padam",
            "Keamanan Lingkungan",
            "Selesai",
            "https://images.unsplash.com/photo-1497436072909-f5e4be8c8e27"
        ),

        PengaduanModel(
            "Caket Hariyah",
            "Pengaduan lampu jalan mati di Prabumulih",
            "Aspirasi Pembangunan",
            "Ditolak",
            "https://images.unsplash.com/photo-1519501025264-65ba15a82390"
        ),

        PengaduanModel(
            "Ahmad Fauzi",
            "Penyusunan laporan hasil pengecekan jalan",
            "Infrastruktur Desa",
            "Selesai",
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee"
        ),

        PengaduanModel(
            "Sari Dewi",
            "Pengaduan sampah menumpuk di pasar",
            "Lingkungan & Kebersihan",
            "Menunggu",
            "https://images.unsplash.com/photo-1473448912268-2022ce9509d8"
        ),

        PengaduanModel(
            "Budi Santoso",
            "Air PDAM tidak mengalir 3 hari",
            "Air Bersih & Sanitasi",
            "Diproses",
            "https://images.unsplash.com/photo-1502303756762-a7dc8ee4d9c0"
        ),

        PengaduanModel(
            "Wulan Dari",
            "Drainase tersumbat menyebabkan banjir",
            "Lingkungan & Kebersihan",
            "Menunggu",
            "https://images.unsplash.com/photo-1502082553048-f009c37129b9"
        ),

        PengaduanModel(
            "Rizky Pratama",
            "Jembatan desa retak perlu perbaikan",
            "Infrastruktur Desa",
            "Diproses",
            "https://images.unsplash.com/photo-1449824913935-59a10b8d2000"
        ),

        PengaduanModel(
            "Anisa Rahma",
            "Penerangan jalan umum mati total",
            "Infrastruktur Desa",
            "Selesai",
            "https://images.unsplash.com/photo-1494526585095-c41746248156"
        ),

        PengaduanModel(
            "Doni Hermawan",
            "Aspirasi pembangunan taman bermain anak",
            "Aspirasi Pembangunan",
            "Menunggu",
            "https://images.unsplash.com/photo-1503454537195-1dcabb73ffb9"
        ),

        PengaduanModel(
            "Fitri Handayani",
            "Selokan di depan rumah tersumbat",
            "Lingkungan & Kebersihan",
            "Diproses",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470"
        ),

        PengaduanModel(
            "Agus Mulyono",
            "Fasilitas posyandu tidak memadai",
            "Pendidikan",
            "Menunggu",
            "https://images.unsplash.com/photo-1523050854058-8df90110c9f1"
        ),

        PengaduanModel(
            "Dewi Kusuma",
            "Pengaduan keamanan lingkungan malam hari",
            "Keamanan Lingkungan",
            "Diproses",
            "https://images.unsplash.com/photo-1516321318423-f06f85e504b3"
        ),

        PengaduanModel(
            "Hendra Putra",
            "Trotoar rusak membahayakan pejalan kaki",
            "Infrastruktur Desa",
            "Selesai",
            "https://images.unsplash.com/photo-1477959858617-67f85cf4f1df"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PengaduanAdapter(pengaduanList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Pengaduan: ${selectedItem.judulPengaduan}\nStatus: ${selectedItem.status}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvPengaduan.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter  // pakai "this.adapter" bukan langsung "adapter"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}