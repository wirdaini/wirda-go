package com.example.wirda_go

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // CEK RULE 1: username == password
            val rule1 = username == password && username.isNotEmpty()

            // CEK RULE 2: username & password cocok dengan data di SharedPreferences
            val userDataPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val userExists = userDataPref.getBoolean("user_$username", false)
            val savedPassword = userDataPref.getString("user_${username}_password", "")
            val rule2 = userExists && savedPassword == password

            // JIKA SALAH SATU RULE TERPENUHI, BOLEH LOGIN
            if (rule1 || rule2) {

                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // TAMPILAN ERROR VALIDASI (bukan Toast/AlertDialog biasa)
                val errorMessage = buildString {
                    if (username.isEmpty()) append("• Username tidak boleh kosong\n")
                    if (password.isEmpty()) append("• Password tidak boleh kosong\n")
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        append("• Username atau password salah\n")
                        append("• Coba gunakan username = password (untuk test)\n")
                        append("• Atau login dengan akun yang sudah terdaftar")
                    }
                }

                AlertDialog.Builder(this)
                    .setTitle("❌ Login Gagal")
                    .setMessage(errorMessage)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}