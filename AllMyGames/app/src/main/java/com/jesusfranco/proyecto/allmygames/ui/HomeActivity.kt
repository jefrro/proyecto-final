package com.jesusfranco.proyecto.allmygames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.jesusfranco.proyecto.allmygames.R
import com.jesusfranco.proyecto.allmygames.databinding.ActivityHomeBinding

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras //Creamos una variable de tipo bundle
        val email = bundle?.getString("email") //Conseguimos el email y lo pasamos a una constante
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "") //Llamamos a la funcion mandandole el email y el provider


    }

    private fun setup(email: String, provider: String) {

        title = "Inicio"
        binding.tvmail.text = email
        binding.tvProveedor.text = provider

        binding.btnlogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}