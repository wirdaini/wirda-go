package com.example.wirda_go

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INIT BINDING
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // AKSI TOMBOL LOGIN
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // VALIDASI LOGIN (sesuai soal: username == password)
            if (username == password) {

                // SIMPAN LOGIN KE SHAREDPREFERENCES
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                // PINDAH KE BASE / MAIN
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // ALERT JIKA SALAH
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}