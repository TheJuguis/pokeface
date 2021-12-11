package com.example.pokeface.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeface.database.Pokemon

import com.squareup.picasso.Picasso
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject
import pokeface.databinding.ItemPkmnBinding

private lateinit var database: DatabaseReference
class LeftAdapter(private val pokemons: List<Pokemon>, private val leftViewModel: LeftViewModel): RecyclerView.Adapter<LeftAdapter.LeftHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeftHolder {
        val binding = ItemPkmnBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LeftHolder(binding, leftViewModel)
    }

    override fun onBindViewHolder(holder: LeftHolder, position: Int) {
        holder.render(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size

    class LeftHolder(val binding: ItemPkmnBinding, val leftViewModel: LeftViewModel): RecyclerView.ViewHolder(binding.root){


        fun render(pokemon: Pokemon){
            val myDB = FirebaseDatabase.getInstance()
            database = myDB.reference
            binding.labelPkmnName.setText(pokemon.PkmnName)
            binding.tvPkmnId.setText(pokemon.PkmnID.toString())
            binding.tvPkmnHP.setText(pokemon.PkmnHp)
            binding.tvPkmnAtk.setText(pokemon.PkmnAtk)
            binding.tvPkmnAtkSpc.setText(pokemon.PkmnAtkSpc)
            binding.tvPkmnDef.setText(pokemon.PkmnDef)
            binding.tvPkmnDefSpc.setText(pokemon.PkmnDefSpc)
            binding.tvPkmnSpd.setText(pokemon.PkmnSpd)
            Picasso.get().load(pokemon.PkmnSprite).into(binding.spritePkmn)
            val user="002"
            binding.btnErasePkmn.setOnClickListener {
                database.child("usuarios").child(user).get().addOnSuccessListener { record->
                    val json = JSONObject(record.value.toString())
                    val numPkmn = json.getInt("total")
                    Log.d("updtNum", "Pokemon totales: $numPkmn")
                    val updtNum = hashMapOf<String, Any>(
                        "usuarios/${user}/total" to numPkmn -1
                    )
                    Log.d("updtNum", "Pokemon actuali: $updtNum")
                    database.updateChildren(updtNum)
                }
                leftViewModel.delete(pokemon)
            }
        }
    }
}