package com.example.wirda_go

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNama: TextInputEditText
    private lateinit var etTanggalLahir: TextInputEditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var spinnerAgama: Spinner
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        initViews()
        setupDatePicker()
        setupSpinnerAgama()
        setupClickListeners()
    }

    private fun initViews() {
        etNama = findViewById(R.id.etNama)
        etTanggalLahir = findViewById(R.id.etTanggalLahir)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        spinnerAgama = findViewById(R.id.spinnerAgama)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)
    }

    private fun setupDatePicker() {
        etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val formattedDate = "$dayOfMonth/${month + 1}/$year"
                    etTanggalLahir.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupSpinnerAgama() {
        val daftarAgama = arrayOf("Pilih Agama", "Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, daftarAgama)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAgama.adapter = adapter
    }

    private fun setupClickListeners() {
        btnRegister.setOnClickListener {
            if (validateInputs()) {
                registerUser()
            }
        }

        tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateInputs(): Boolean {
        val nama = etNama.text.toString().trim()
        val tanggalLahir = etTanggalLahir.text.toString().trim()
        val jenisKelamin = when (rgJenisKelamin.checkedRadioButtonId) {
            R.id.rbLaki -> "Laki-laki"
            R.id.rbPerempuan -> "Perempuan"
            else -> ""
        }
        val agama = spinnerAgama.selectedItem.toString()
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        when {
            nama.isEmpty() -> {
                etNama.error = "Nama harus diisi"
                return false
            }
            tanggalLahir.isEmpty() -> {
                etTanggalLahir.error = "Tanggal lahir harus diisi"
                return false
            }
            jenisKelamin.isEmpty() -> {
                Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
                return false
            }
            agama == "Pilih Agama" -> {
                Toast.makeText(this, "Pilih agama", Toast.LENGTH_SHORT).show()
                return false
            }
            username.isEmpty() -> {
                etUsername.error = "Username harus diisi"
                return false
            }
            isUsernameExists(username) -> {
                etUsername.error = "Username sudah terdaftar"
                return false
            }
            password.isEmpty() -> {
                etPassword.error = "Password harus diisi"
                return false
            }
            password.length < 6 -> {
                etPassword.error = "Password minimal 6 karakter"
                return false
            }
            confirmPassword.isEmpty() -> {
                etConfirmPassword.error = "Konfirmasi password harus diisi"
                return false
            }
            password != confirmPassword -> {
                etConfirmPassword.error = "Password tidak cocok"
                return false
            }
        }
        return true
    }

    private fun isUsernameExists(username: String): Boolean {
        return sharedPref.contains("user_$username")
    }

    private fun registerUser() {
        val nama = etNama.text.toString().trim()
        val tanggalLahir = etTanggalLahir.text.toString().trim()
        val jenisKelamin = if (rgJenisKelamin.checkedRadioButtonId == R.id.rbLaki) "Laki-laki" else "Perempuan"
        val agama = spinnerAgama.selectedItem.toString()
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        val editor = sharedPref.edit()
        editor.putString("user_${username}_nama", nama)
        editor.putString("user_${username}_tanggallahir", tanggalLahir)
        editor.putString("user_${username}_jeniskelamin", jenisKelamin)
        editor.putString("user_${username}_agama", agama)
        editor.putString("user_${username}_password", password)
        editor.putBoolean("user_$username", true)
        editor.apply()

        Toast.makeText(this, "Registrasi berhasil! Silakan login.", Toast.LENGTH_LONG).show()
        finish()
    }
}