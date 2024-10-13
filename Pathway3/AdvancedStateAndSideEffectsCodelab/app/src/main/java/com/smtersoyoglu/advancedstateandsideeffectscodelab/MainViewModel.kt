package com.smtersoyoglu.advancedstateandsideeffectscodelab

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _uiState = mutableStateOf<UiState>(UiState.Idle)
    val uiState : State<UiState> get() = _uiState

    // Metin girişini tutacak
    var inputName by mutableStateOf("")

    fun onNameChanged(newName: String) {
        inputName = newName
    }

    fun onSubmit() {
        // Burada bir yükleme durumu gösterebiliriz
        _uiState.value = UiState.Loading

        // Burada bir yükleme durumu gösterebiliriz
        if (inputName.isNotBlank()) {
            _uiState.value = UiState.Success(inputName)
        } else {
            _uiState.value = UiState.Error("Lütfen bir isim girin")
        }
    }
}