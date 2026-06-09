package com.example.wirda_go.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Memberitahu ViewPager2 bahwa total tab ada 3
    override fun getItemCount(): Int = 3

    // Menentukan Fragment mana yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()   // Tab Informasi
            1 -> TabBFragment()   // Tab Layanan
            2 -> TabCFragment()   // Tab Pengaduan (RecyclerView)
            else -> throw IllegalStateException("Posisi tab tidak valid: $position")
        }
    }
}