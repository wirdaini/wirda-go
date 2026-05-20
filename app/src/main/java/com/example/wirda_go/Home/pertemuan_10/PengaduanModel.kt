package com.example.wirda_go.Home.pertemuan_10

data class PengaduanModel(
    val namaPelapor: String,      // Nama warga yang melapor
    val judulPengaduan: String,   // Judul singkat pengaduan
    val kategori: String,         // Kategori: Infrastruktur, Lingkungan, dll
    val status: String,           // Status: Menunggu, Diproses, Selesai
    val avatarUrl: String         // URL foto avatar pelapor
)