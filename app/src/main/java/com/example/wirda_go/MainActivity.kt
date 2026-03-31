package com.example.wirda_go

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Layang-layang
    var SisiPendek: Double? = null
    var SisiPanjang: Double? = null
    var Diagonal1: Double? = null
    var Diagonal2: Double? = null
    var keliling: Double? = null
    var luas: Double? = null

    // Bola
    var JariJari: Double? = null
    var Volume: Double? = null
    var LuasBola: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LAYANG-LAYANG
        val etSisiPendek = findViewById<EditText>(R.id.etSisiPendek)
        val etSisiPanjang = findViewById<EditText>(R.id.etSisiPanjang)
        val etDiagonal1 = findViewById<EditText>(R.id.etDiagonal1)
        val etDiagonal2 = findViewById<EditText>(R.id.etDiagonal2)
        val btnKeliling = findViewById<Button>(R.id.btnKeliling)
        val btnLuas = findViewById<Button>(R.id.btnLuas)
        val tvKeliling = findViewById<TextView>(R.id.tvKeliling)
        val tvLuas = findViewById<TextView>(R.id.tvLuas)

        // BOLA
        val etJariJari = findViewById<EditText>(R.id.etJariJari)
        val btnVolume = findViewById<Button>(R.id.btnVolume)
        val btnLuasBola = findViewById<Button>(R.id.btnLuasBola)
        val tvVolume = findViewById<TextView>(R.id.tvVolume)
        val tvLuasBola = findViewById<TextView>(R.id.tvLuasBola)

        // Hitung Keliling Layang-layang
        btnKeliling.setOnClickListener {
            SisiPanjang = etSisiPendek.text.toString().toDouble()
            SisiPendek = etSisiPanjang.text.toString().toDouble()
            keliling = 2 * (SisiPendek!! + SisiPanjang!!)
        }

        // Hitung Luas Layang-layang
        btnLuas.setOnClickListener {
            if (etDiagonal1.text.toString().trim().isEmpty()
                || etDiagonal2.text.toString().trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                Diagonal1 = etDiagonal1.text.toString().toDouble()
                Diagonal2 = etDiagonal2.text.toString().toDouble()
                luas = Diagonal1!! * Diagonal2!! / 2
                tvLuas.setText(luas.toString())
            }
        }

        // Hitung Volume Bola
        btnVolume.setOnClickListener {
            if (etJariJari.text.toString().trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                JariJari = etJariJari.text.toString().toDouble()
                Volume = 4 * (3.14 * JariJari!! * JariJari!! * JariJari!!) / 3
                tvVolume.setText(Volume.toString())
            }
        }

        // Hitung Luas Bola
        btnLuasBola.setOnClickListener {
            if (etJariJari.text.toString().trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                JariJari = etJariJari.text.toString().toDouble()
                LuasBola = 4 * 3.14 * JariJari!! * JariJari!!
                tvLuasBola.setText(LuasBola.toString())
            }
        }
    }
}

