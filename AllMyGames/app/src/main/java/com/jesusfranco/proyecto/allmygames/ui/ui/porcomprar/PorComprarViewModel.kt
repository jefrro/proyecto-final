package com.jesusfranco.proyecto.allmygames.ui.ui.porcomprar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PorComprarViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Juegos por comprar"
    }
    val text: LiveData<String> = _text
}