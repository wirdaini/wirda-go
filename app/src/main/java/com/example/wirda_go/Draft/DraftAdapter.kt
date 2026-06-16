package com.example.wirda_go.Draft

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wirda_go.data.entity.DraftAspirasiEntity
import com.example.wirda_go.databinding.ItemDraftBinding

class DraftAdapter(
    private val draftList: List<DraftAspirasiEntity>,
    private val onDeleteClick: (DraftAspirasiEntity) -> Unit
) : RecyclerView.Adapter<DraftAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDraftBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDraftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = draftList[position]
        holder.binding.tvJudul.text = item.judul
        holder.binding.tvIsiAspirasi.text = item.isiAspirasi
        holder.binding.tvLokasi.text = "📍 ${item.lokasi}"
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(item) }
    }

    override fun getItemCount(): Int = draftList.size
}