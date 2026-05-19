package com.example.wirda_go.About

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.wirda_go.R
import com.example.wirda_go.databinding.ItemInfoBinding
import com.google.android.material.snackbar.Snackbar

class InfoAdapter(
    context: Context,
    private val items: List<InfoModel>
) : ArrayAdapter<InfoModel>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate layout item_info.xml
        val binding = ItemInfoBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        val data = items[position]

        // Load gambar dari URL menggunakan Glide
        Glide.with(context)
            .load(data.iconUrl)
            .circleCrop()  // Bikin gambar jadi bulat
            .placeholder(R.drawable.ic_complaint)  // Gambar sementara saat loading
            .into(binding.imgIcon)

        // Set teks
        binding.tvTitle.text = data.title
        binding.tvDescription.text = data.description

        // Klik pada setiap item
        binding.root.setOnClickListener {
            Snackbar.make(
                parent,
                "Info: ${data.title}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}