package com.jparnaudo.apcentro22.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.jparnaudo.apcentro22.R
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private var listener: OnFragmentActionsListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            view.etName.setText(user.displayName.toString())
            view.etPhone.setText(user.phoneNumber.toString())
            view.ivProfile.setImageURI(user.photoUrl)
        }

        view.switchEditar.setOnClickListener {Boolean
            if (switchEditar.isChecked){

                //habilito los campos para cambiar nombre, telefono, edad
                etName.isEnabled = true
                etPhone.isEnabled = true
                etAge.isEnabled = true
                Toast.makeText(activity,"Sí",Toast.LENGTH_SHORT).show()



            }else {
                //se guardan los cambios en firebase
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(etName.toString())
//                    .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                    .build()

                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                        }
                    }

                etName.isEnabled = false
                etPhone.isEnabled = false
                etAge.isEnabled = false
                Toast.makeText(activity,"noooooo",Toast.LENGTH_SHORT).show()

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
