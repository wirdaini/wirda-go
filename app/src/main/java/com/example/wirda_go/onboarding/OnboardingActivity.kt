package com.example.wirda_go.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.AuthActivity
import com.example.wirda_go.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            OnboardingFragment1(),
            OnboardingFragment2(),
            OnboardingFragment3()
        )

        val adapter = OnboardingAdapter(this, fragments)
        binding.onboardingViewPager.adapter = adapter

        binding.dotIndicator.attachTo(binding.onboardingViewPager)

        binding.onboardingViewPager.registerOnPageChangeCallback(
            object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == fragments.size - 1) {
                        binding.btnNext.visibility = View.GONE
                        binding.btnStart.visibility = View.VISIBLE
                    } else {
                        binding.btnNext.visibility = View.VISIBLE
                        binding.btnStart.visibility = View.GONE
                    }
                }
            }
        )

        binding.btnNext.setOnClickListener {
            val current = binding.onboardingViewPager.currentItem
            binding.onboardingViewPager.currentItem = current + 1
        }

        binding.btnStart.setOnClickListener {
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putBoolean("onboarding_done", true)
                .apply()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        binding.tvSkip.setOnClickListener {
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putBoolean("onboarding_done", true)
                .apply()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}