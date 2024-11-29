package com.example.kt_06_persistencia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kt_06_persistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
        var key : String
        binding.btnEjecutar.setOnClickListener{
            val editor = preferencias.edit()
            editor.putString("mail", binding.etNombre.text.toString())
            editor.apply()
            finish()
        }
        binding.btnRecuperar.setOnClickListener{
            key = "mail"
            val valor = preferencias.getString(key, null)
            binding.etNombre.setText(valor)
        }
        binding.btnSiguiente.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}