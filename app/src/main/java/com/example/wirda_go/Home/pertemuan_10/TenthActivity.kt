package com.example.wirda_go.Home.pertemuan_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wirda_go.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator
import androidx.core.content.ContextCompat

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Suara Rakyat"
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 2. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 3. Hubungkan Adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 4. Hubungkan TabLayout dengan ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Informasi"
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_info_details)
                }
                1 -> {
                    tab.text = "Layanan"
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_agenda)
                }
                2 -> {
                    tab.text = "Pengaduan"
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_edit)
                }
            }
        }.attach()
    }
}