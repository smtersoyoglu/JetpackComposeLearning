package com.smtersoyoglu.navigationcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.smtersoyoglu.navigationcodelab.ui.theme.NavigationCodelabTheme
class MainActivity () : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationCodelabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController() // NavController oluşturuluyor, navigasyon işlemleri için kullanılır.

                    // currentBackStackEntryAsState ile güncel ekranı izleyin.
                    val currentBackStackEntry by navController.currentBackStackEntryAsState()

                    /* Hangi ekranın aktif olduğunu bulmak için doğru bir yaklaşımdır. Ancak bu tanım Screen.Second için bir dinamik rota (örneğin, second/{url}) olduğunda,
                    bu rota doğrudan currentBackStackEntry?.destination?.route ile eşleşmez. Bu durumda, Screen.Second'in doğru olarak seçilmemesi nedeniyle RallyTabRow'da hala Home sekmesi aktif görünüyor.
                    Bu durumda, daha doğru bir kontrol ile güncellenmelidir.
                    */

                    // Güncel ekranı alarak currentScreen'i belirliyoruz
                    val currentScreen = when (currentBackStackEntry?.destination?.route) {
                        Screen.Home.route -> Screen.Home
                        Screen.Second.route + "/{url}" -> Screen.Second // Dinamik rota için
                        Screen.Last.route -> Screen.Last
                        else -> Screen.Home // Geçersiz bir durum olduğunda varsayılan olarak Home ekranını kullan
                    }

                    Scaffold(
                        topBar = {
                            // RallyTabRow, sekmeleri içeren üst çubuk
                            RallyTabRow(
                                allScreens = listOf(Screen.Home, Screen.Second, Screen.Last),
                                onTabSelected = { newScreen -> // Yeni sekmeye tıklandığında çalışacak fonksiyon
                                    if (newScreen is Screen.Second) {
                                        // Second rotası için bir URL ile gezin
                                        navController.navigateSingleTopTo("${newScreen.route}/example_url") // Dinamik rota için örnek bir URL kullanıyoruz
                                    } else {
                                        navController.navigateSingleTopTo(newScreen.route) // Diğer ekranlar için normal gezinme
                                    }
                                },
                                currentScreen = currentScreen, // Geçerli ekranı belirliyoruz
                            )
                        }
                    ) { innerPadding ->
                        // Navigasyon grafiğini tanımlıyoruz
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route, // Başlangıç ekranı olarak Home ayarlanıyor
                            Modifier.padding(innerPadding) // İç boşluk ekleyerek mevcut alanı koruyoruz
                        ) {
                            // Home ekranı için navigasyon
                            composable(Screen.Home.route) {
                                HomeScreen(navController = navController) // Home ekranını göster
                            }
                            // Dinamik URL içeren Second ekranı için navigasyon
                            composable(
                                Screen.Second.route + "/{url}", // Dinamik URL yapısını tanımlıyoruz
                                arguments = listOf(navArgument("url") { type = NavType.StringType }) // URL'yi argüman olarak alıyoruz
                            ) { backStackEntry ->
                                // SecondScreen'e navigasyon
                                SecondScreen(
                                    navController = navController,
                                    backStackEntry = backStackEntry // Geriye dönme işlemleri için kullanılır
                                )
                            }
                            // Last ekranı için navigasyon
                            composable(Screen.Last.route) {
                                LastScreen(navController = navController) // Last ekranını göster
                            }
                        }
                    }
                }
            }
        }
    }
}

// RallyTabRow, tüm sekmeleri görüntüleyen bir bileşen. Bu bileşeni örnek bir allScreens listesi ve currentScreen ile oluşturuyoruz.
@Composable
fun RallyTabRow(
    allScreens: List<Screen>, // Tüm ekranları içeren liste
    onTabSelected: (Screen) -> Unit, // Sekme seçildiğinde çalışacak fonksiyon
    currentScreen: Screen, // Geçerli ekran
    modifier: Modifier = Modifier // Opsiyonel bir modifier
) {
    TabRow(
        selectedTabIndex = allScreens.indexOf(currentScreen), // Seçili sekmeyi bul
        modifier = Modifier.padding(top = 44.dp) // Üstten boşluk ekliyoruz
    ) {
        // Her bir ekran için sekme oluştur
        allScreens.forEach { screen ->
            Tab(
                text = { Text(screen.route) }, // Sekme metni olarak ekran rotasını kullan
                selected = screen == currentScreen, // Sekme seçili mi kontrol et
                onClick = { onTabSelected(screen) } // Tıklandığında yeni ekranı seç
            )
        }
    }
}

// Navigasyon işlemlerini basit hale getirmek için bir uzantı fonksiyonu tanımlıyoruz
fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true // Mevcut durum kaydedilecek
        }
        launchSingleTop = true // Yeni ekran tek seferde açılacak
        restoreState = true // Geçmiş durum geri yüklenecek
    }

/**    Bu kod, her sekmeye yalnızca tek bir kopya ile gidilmesini sağlar ve geri döndüğünüzde durumun korunmasına olanak tanır. */
}
