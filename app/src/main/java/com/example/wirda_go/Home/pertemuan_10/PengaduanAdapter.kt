package com.example.wirda_go.Home.pertemuan_10

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wirda_go.databinding.ItemPengaduanBinding

class PengaduanAdapter(
    private val pengaduanList: List<PengaduanModel>,
    private val onItemClick: (PengaduanModel) -> Unit
) : RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder>() {

    inner class PengaduanViewHolder(val binding: ItemPengaduanBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengaduanViewHolder {
        val binding = ItemPengaduanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PengaduanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PengaduanViewHolder, position: Int) {
        val item = pengaduanList[position]

        with(holder.binding) {
            tvNamaPelapor.text = item.namaPelapor
            tvJudulPengaduan.text = item.judulPengaduan
            tvKategori.text = "📁 ${item.kategori}"
            tvStatus.text = item.status

            // Warna badge status berbeda tergantung kondisi
            val bgColor = when (item.status) {
                "Menunggu"  -> "#F59E0B"  // kuning
                "Diproses"  -> "#3B82F6"  // biru
                "Selesai"   -> "#10B981"  // hijau
                "Ditolak"   -> "#EF4444"  // merah
                else        -> "#6B7280"  // abu-abu
            }
            tvStatus.setBackgroundColor(Color.parseColor(bgColor))

            // Load avatar dengan Glide
            Glide.with(holder.itemView.context)
                .load(item.avatarUrl)
                .into(imgAvatar)

            // Aksi klik seluruh item
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = pengaduanList.size
}