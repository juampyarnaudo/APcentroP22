package com.jparnaudo.apcentro22

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.jparnaudo.apcentro22.fragments.ContactoFragment
import com.jparnaudo.apcentro22.fragments.OnFragmentActionsListener
import com.jparnaudo.apcentro22.fragments.PerfilFragment
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType {
    BASIC, GOOGLE
}

class HomeActivity : AppCompatActivity(), OnFragmentActionsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(PerfilFragment())
        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        // guardado de datos

        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()


//Carga de fragments
        btnProfile.setOnClickListener { loadFragment(PerfilFragment()) }
        btnContact.setOnClickListener { replaceFragment(ContactoFragment()) }
        animation_view.setOnClickListener {
            Toast.makeText(this,"hola",Toast.LENGTH_SHORT).show()
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    override fun onClickFragmentButton() {
        Toast.makeText(this, "El botón ha sido pulsado", Toast.LENGTH_SHORT).show()
    }




    private fun setup(email: String, provider: String) {
        title = "Inicio"
//        mtvEmail.text = email
//        mtvProvider.text = provider

        btnLogOut.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            goToActivity<AuthActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            FirebaseAuth.getInstance().signOut()
            finish()
        }
    }



    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Presione atras otra vez para salir...", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
