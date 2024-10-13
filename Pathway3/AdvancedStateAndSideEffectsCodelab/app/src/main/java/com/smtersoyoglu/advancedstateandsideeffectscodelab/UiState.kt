package com.smtersoyoglu.advancedstateandsideeffectscodelab

sealed class UiState {
    object Idle : UiState() // Kullanıcı bir şey girmediğinde
    object Loading : UiState() // Veri yükleniyor
    data class Success(val name: String) : UiState() // Başarılı giriş
    data class Error(val message: String) : UiState() // Hata durumu
}
