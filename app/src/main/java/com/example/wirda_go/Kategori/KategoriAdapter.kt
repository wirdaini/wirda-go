package com.example.wirda_go.Kategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wirda_go.data.entity.KategoriPengaduanEntity
import com.example.wirda_go.databinding.ItemKategoriBinding

class KategoriAdapter(
    private val kategoriList: List<KategoriPengaduanEntity>,
    private val onDeleteClick: (KategoriPengaduanEntity) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = kategoriList[position]
        holder.binding.tvNamaKategori.text = item.namaKategori
        holder.binding.tvDeskripsi.text = item.deskripsi
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(item) }
    }

    override fun getItemCount(): Int = kategoriList.size
}