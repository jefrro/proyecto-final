package com.jesusfranco.proyecto.allmygames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.jesusfranco.proyecto.allmygames.R
import com.jesusfranco.proyecto.allmygames.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }




    override fun onStart() {
        super.onStart()

        setup()


        binding.btncancelar.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }


    private fun setup(){
        title = "Registro"


        binding.btnregistro.setOnClickListener{

            val email = binding.tieUsermail.text.toString()
            val password = binding.tiePassword.text.toString()
            val password2 = binding.tieConfirmPassword.text.toString()

            if ((email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) && (password == password2)){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful){ //Siempre va a existir si es successful
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                            Toast.makeText(applicationContext, "Ya puede loquearse con su cuenta!" , Toast.LENGTH_LONG).show()

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
        builder.setMessage("Se ha producido un error registrando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Compruebe que los datos son correctos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}