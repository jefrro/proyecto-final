package com.jesusfranco.proyecto.allmygames.ui.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritosViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Mis juegos favoritos"
    }
    val text: LiveData<String> = _text
}