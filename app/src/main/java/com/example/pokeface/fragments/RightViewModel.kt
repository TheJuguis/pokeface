package com.example.pokeface.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeface.database.DatabaseManager
import com.example.pokeface.database.MyAppDataSource
import com.example.pokeface.database.Pokemon
import kotlinx.coroutines.launch

class RightViewModel: ViewModel() {
    fun save(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).save(pokemon)
        }
    }
}