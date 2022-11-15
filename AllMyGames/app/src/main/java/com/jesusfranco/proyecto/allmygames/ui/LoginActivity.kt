package com.jesusfranco.proyecto.allmygames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.jesusfranco.proyecto.allmygames.R
import com.jesusfranco.proyecto.allmygames.databinding.ActivityLoginBinding
import com.jesusfranco.proyecto.allmygames.databinding.ActivityRegistroBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        //Splash
        Thread.sleep(2000) //HACK
        setTheme(R.style.AppTheme)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)



        setup()



        binding.btnRegistro.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
            finish()
        }
    }

    private fun setup(){
        title = "Login"

        binding.btnLogin.setOnClickListener{


            val email = binding.tieUsermail.text.toString()
            val password = binding.tiePassword.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful){ //Siempre va a existir si es successful
                            showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }else{
                showAlert2()
            }
        }


    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Rellena todos los campos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}