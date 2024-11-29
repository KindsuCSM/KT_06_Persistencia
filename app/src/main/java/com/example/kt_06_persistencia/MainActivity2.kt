package com.example.kt_06_persistencia

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kt_06_persistencia.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE)

        binding.btnGrabar.setOnClickListener{
            val editor = preferencias.edit()
            editor.putString(binding.etIngresarNombre.text.toString(), binding.etDatos.text.toString())
            editor.apply()
            Toast.makeText(this, "Datos grabados", Toast.LENGTH_LONG).show()
            binding.etIngresarNombre.setText("")
            binding.etDatos.setText("")

        }
        binding.btnCargar.setOnClickListener{
            val datos = preferencias.getString(binding.etIngresarNombre.text.toString(), "")
            if(datos != null){
                if(datos.length == 0){
                    Toast.makeText(this, "No existe dicho nombre en la agenda", Toast.LENGTH_LONG).show()
                }else{
                    binding.etDatos.setText(datos)
                }
            }
        }
    }
}