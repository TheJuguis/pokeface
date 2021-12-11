package com.example.pokeface.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject
import pokeface.R
import pokeface.databinding.FragmentHomeBinding

private lateinit var database: DatabaseReference
class HomeFragment : Fragment() {

    private  lateinit var binding: FragmentHomeBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference

        database.child("usuarios").child("002").get().addOnSuccessListener { record ->
            val json = JSONObject(record.value.toString())
            Log.d("ValoresFirebase", "got value ${record.value}")
            binding.tvName.setText(json.getString("nombre").toString())
            binding.tvLastname.setText(json.getString("apellido").toString())
            binding.tvnickName.setText(json.getString("nickname").toString())
            binding.tvNivel.setText(json.getString("nivel"))
            binding.tvNumPkmn.setText(json.getString("total"))
        }
        return binding.root












    }

    //INICIA BUSCAR PERFIL
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onViewCreated(view,savedInstanceState)


        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference

        /*
        val userID = view?.findViewById<EditText>(R.id.etUserId)
        val userName = view?.findViewById<EditText>(R.id.etUserName)
        val userMail = view?.findViewById<EditText>(R.id.etUserEmail)
        val userLevel = view?.findViewById<EditText>(R.id.etUserlevel)
        val userCapt = view?.findViewById<EditText>(R.id.etUserCapturas)

        */
        val userToGet = view?.findViewById<EditText>(R.id.etUserIdToGet)

        //val btnSend = view?.findViewById<Button>(R.id.btnSet)
        val btnGet = view?.findViewById<Button>(R.id.btnGet)
        val userNameGet = view?.findViewById<TextView>(R.id.userNameGet)
        val userEmailGet = view?.findViewById<TextView>(R.id.userEmailGet)
        val userLevelGet = view?.findViewById<TextView>(R.id.userLevelGet)
        val userNumCapturasGet = view?.findViewById<TextView>(R.id.userNumCapturasGet)


        /*
        btnSend.setOnClickListener {
            writeNewUser(userID.text.toString(), userName.text.toString(), userMail.text.toString(), userLevel.text.toString(),userCapt.text.toString() )
            userID.text.clear()
            userName.text.clear()
            userMail.text.clear()
            userLevel.text.clear()
            userCapt.text.clear()
        }

         */

        btnGet.setOnClickListener {
            getUser(userToGet.text.toString(), userNameGet, userEmailGet,userLevelGet,userNumCapturasGet)

        }


    }

    fun getUser(userId: String, userNameget: TextView, userEmailget: TextView, userLevelGet: TextView, userNumCapturasGet: TextView){
        database.child("usuarios").child(userId).get().addOnSuccessListener { record ->
            val json = JSONObject(record.value.toString())
            Log.d("ValoresFirebase", "got value ${record.value}")
            userNameget.setText("NOMBRE DE ENTRENADOR: ${json.getString("nombre")}")
            userEmailget.setText("NICKNAME DE ENTRENADOR: ${json.getString("nickname")}")
            userLevelGet.setText("NIVEL DE ENTRENADOR: ${json.getString("nivel")}")
            userNumCapturasGet.setText("POKEMONS EN POSESION: ${json.getString("total")}")


        }
    }







}




