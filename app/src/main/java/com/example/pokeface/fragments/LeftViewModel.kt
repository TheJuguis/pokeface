package com.example.pokeface.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeface.database.DatabaseManager
import com.example.pokeface.database.MyAppDataSource
import com.example.pokeface.database.Pokemon
import kotlinx.coroutines.launch

class LeftViewModel:ViewModel() {
    val savedPokemon = MutableLiveData<List<Pokemon>>()
    fun getPokemons(){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            savedPokemon.value = MyAppDataSource(pokemonDao).getPokemons().value
        }
    }

    fun delete(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).delete(pokemon)
            getPokemons()
        }
    }
}