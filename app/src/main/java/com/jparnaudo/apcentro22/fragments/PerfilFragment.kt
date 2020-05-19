package com.jparnaudo.apcentro22.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jparnaudo.apcentro22.R
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private var listener: OnFragmentActionsListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)
        val usuario = FirebaseAuth.getInstance().currentUser
//        if (usuario != null) {
//            val user: FirebaseUser = auth.currentUser!!
//            val currentUserDb = databaseReference.child(user.uid)
//            view.etName.setText(currentUserDb.child("Name").toString())
//            view.etPhone.setText(currentUserDb.child("Phone").toString())
//            view.etAge.setText(currentUserDb.child("Age").toString())
//
//        }
//        Database Firebase

/*Creamos una instancia para guardar los datos del usuario en nuestra base  de datos*/
        database = FirebaseDatabase.getInstance()
/*Creamos una instancia para crear nuestra autenticación y guardar el usuario*/
        auth = FirebaseAuth.getInstance()

/*reference nosotros leemos o escribimos en una ubicación específica la base de datos*/
        databaseReference = database.reference.child("Users")
//        database = FirebaseDatabase.getInstance().reference

        view.switchEditar.setOnClickListener {
            Boolean
            if (switchEditar.isChecked) {
                switchEditar.setText("pulsa nuevamente para Guardar")
                //habilito los campos para cambiar nombre, telefono, edad
                etName.isEnabled = true
                etPhone.isEnabled = true
                etAge.isEnabled = true
                Toast.makeText(activity, "Sí", Toast.LENGTH_SHORT).show()
                if (usuario != null) {
                    val user: FirebaseUser = auth.currentUser!!
                    val currentUserDb = databaseReference.child(user.uid)
                    view.etName.setText(currentUserDb.child("Name").toString())
                    view.etPhone.setText(currentUserDb.child("Phone").toString())
                    view.etAge.setText(currentUserDb.child("Age").toString())
                }
            } else {
                switchEditar.setText("pulsa para Editar")
                //se guardan los cambios en firebase
                val user: FirebaseUser = auth.currentUser!!
                val currentUserDb = databaseReference.child(user.uid)
                currentUserDb.child("Age").setValue(etAge.text.toString())
                currentUserDb.child("Name").setValue(etName.text.toString())
                currentUserDb.child("Phone").setValue(etPhone.text.toString())



                etName.isEnabled = false
                etPhone.isEnabled = false
                etAge.isEnabled = false
                Toast.makeText(
                    activity,
                    "Los cambios se guardaron correctamente",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }



        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        btnPlus.setOnClickListener { listener?.onClickFragmentButton() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
