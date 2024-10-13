package com.smtersoyoglu.advancedstateandsideeffectscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smtersoyoglu.advancedstateandsideeffectscodelab.ui.theme.AdvancedStateAndSideEffectsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedStateAndSideEffectsCodelabTheme {
                //MyAppScreen()
                MainScreen()
            }
        }
    }
}

/*
@Composable
fun MyAppScreen() {
    MyAppTopAppBar(
        topAppBarText = "My App Title",
        onBackPressed = {
            // Geri butonuna basıldığında yapılacak işlemler
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppScreenPreview() {
    MyAppScreen()
}

 */
/**
 * Bu örnekte:
 * ViewModel: Kullanıcıdan alınan metin girişini tutar ve durumu günceller.
 * State: UiState sealed class'ı, uygulamanın farklı durumlarını temsil eder.
 * Event: Kullanıcı bir isim girdiğinde ve butona bastığında onSubmit metodu tetiklenir.
 * Bu yapıyı kullanarak, kullanıcı etkileşimlerini daha iyi yönetebilir ve uygulamanın farklı durumlarına göre kullanıcı arayüzünü güncelleyebilirsin.
 * Bu, Jetpack Compose ile unidirectional data flow (tek yönlü veri akışı) yapısını anlaman için iyi bir örnek olacaktır.
 */
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                Text(text = "Merhaba: ${(uiState as UiState.Success).name}")
            }

            is UiState.Error -> {
                Text(text = "Hata: ${(uiState as UiState.Error).message}")
            }

            is UiState.Idle -> {
                OutlinedTextField(
                    value = viewModel.inputName,
                    onValueChange = { viewModel.onNameChanged(it) },
                    label = { Text("İsminizi Girin") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.onSubmit() }) {
                    Text("Gönder")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}