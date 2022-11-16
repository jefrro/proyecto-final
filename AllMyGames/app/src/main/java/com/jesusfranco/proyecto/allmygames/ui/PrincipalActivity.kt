package com.jesusfranco.proyecto.allmygames.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jesusfranco.proyecto.allmygames.R
import com.jesusfranco.proyecto.allmygames.databinding.ActivityPrincipalBinding
import com.jesusfranco.proyecto.allmygames.databinding.NavHeaderPrincipalBinding

//Clase enum para distintos proveedores de autenticacion como correo, google, facebook..
enum class ProviderType{
    BASIC
}

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPrincipal.toolbar)

//        binding.appBarPrincipal.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_completados, R.id.nav_pendientes , R.id.nav_por_comprar, R.id.nav_favoritos), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()


//BOTON PRINCIPAL
        binding.appBarPrincipal.fab.setOnClickListener{

        }



        val bundle = intent.extras //Creamos una variable de tipo bundle
        val email = bundle?.getString("email") //Conseguimos el email y lo pasamos a una constante
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "") //Llamamos a la funcion mandandole el email y el provider





    }
    private fun setup(email: String, provider: String) {

        val navViewBinding = NavHeaderPrincipalBinding.bind(binding.navView.getHeaderView(0))
        navViewBinding.txtMail.text = email
        navViewBinding.txtProvider.text = provider


        navViewBinding.btnlogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}