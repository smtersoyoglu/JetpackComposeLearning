package com.smtersoyoglu.basiclayoutscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.smtersoyoglu.basiclayoutscodelab.ui.theme.BasicLayoutsCodelabTheme


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab1_inversions to R.string.ab1_inversions,
).map { DrawableStringPair(it.first, it.second) }


private val favoriteCollectionsData = listOf(
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
).map { DrawableStringPair(it.first, it.second) }


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Cihazın ekran boyutunu hesapla
            val windowSizeClass = calculateWindowSizeClass(this)
            // Ekran boyutuna göre MySootheApp'i başlat
            MySootheApp(windowSizeClass)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface

        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
//.height() bileşenin yüksekliğini sabit bir değere ayarlarken,
// .heightIn() minimum veya maksimum yükseklik sınırlarını belirler.
// Yani, .height() ile yükseklik her zaman aynı kalır, ancak .heightIn() bileşenin yüksekliğini ayarlarken esneklik sağlar (örneğin, minimum 56dp olabilir ama daha fazla da olabilir).
    )

}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    BasicLayoutsCodelabTheme {
        SearchBar()
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
//horizontalAlignment = Alignment.CenterHorizontally: İçindeki tüm bileşenlerin yatay olarak ortalanmasını sağlar.
    ) {
        Image(
            painter = painterResource(R.drawable.ab1_inversions),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
// contentScale = ContentScale.Crop: Resmin kırpılarak tam olarak sığmasını sağlar.
// size(88.dp): Resmi 88 dp'lik bir kare olacak şekilde boyutlandırır.
// clip(CircleShape): Resmi yuvarlak bir şekle dönüştürür.
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutsCodelabTheme {
        AlignYourBodyElement(
            text = R.string.ab1_inversions,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,   // Drawable kaynağı olarak bir resim almak için
    @StringRes text: Int,         // String kaynağı olarak bir metin almak için
    modifier: Modifier = Modifier // İsteğe bağlı bir `Modifier` parametresi
) {
    Surface( // Bir yüzey (arka plan) oluşturan `Surface` bileşeni
        shape = MaterialTheme.shapes.medium, // Orta büyüklükte yuvarlatılmış köşelerle şekil ver
        color = MaterialTheme.colorScheme.surfaceVariant, // Arka plan rengini `surfaceVariant` olarak ayarla
        modifier = modifier // Dışarıdan gelen `modifier` parametresini uygula
    ) {
        Row( // Bileşenleri yatay olarak yerleştirmek için bir `Row` (Satır) bileşeni
            verticalAlignment = Alignment.CenterVertically, // Dikeyde ortalama hizası
            modifier = Modifier.width(255.dp) // Satırın genişliğini 255 dp olarak ayarla
        ) {
            Image( // Bir resim görüntülemek için `Image` bileşeni
                painter = painterResource(R.drawable.fc2_nature_meditations), // Resim kaynağı
                contentDescription = null, // Erişilebilirlik açıklaması, burada gerek yok
                contentScale = ContentScale.Crop, // Resmi kırparak tam sığdır
                modifier = Modifier.size(80.dp) // Resmin boyutunu 80 dp kare yap
            )
            Text( // Metin bileşeni
                text = stringResource(text), // Metin kaynağından alınan değeri göster
                style = MaterialTheme.typography.titleMedium, // `titleMedium` stilini uygula
                modifier = Modifier.padding(horizontal = 16.dp) // Yatayda 16 dp boşluk ver
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF5F0EE
) // Önizleme modu, arka plan rengiyle birlikte
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutsCodelabTheme { // Temayı uygula
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations, // String kaynağı
            drawable = R.drawable.fc2_nature_meditations, // Drawable kaynağı
            modifier = Modifier.padding(8.dp) // Kartın dış boşluklarını ayarla
        )
    }
}

// LazyRow: Yatay kaydırılabilir bir liste oluşturur ve sadece ekrandaki öğeleri yükleyerek performans sağlar.
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    //LazyRow, öğeleri yatay bir satırda sıralar ve kaydırma imkanı verir.
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Öğeler arası boşluk
        contentPadding = PaddingValues(horizontal = 16.dp), // İlk ve son öğe için kenar boşluğu
        modifier = modifier
    ) {
        // alignYourBodyData listesindeki her bir öğeyi gösteriyoruz
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}


@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // LazyHorizontalGrid: Yatay kaydırılabilir bir grid oluşturur
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2), // Grid'de sabit 2 satır olacağını belirtiyoruz
        contentPadding = PaddingValues(horizontal = 16.dp), // Grid'in yatay kenarlarına 16dp boşluk ekliyoruz
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Yatayda her öğe arasında 16dp boşluk bırakıyoruz
        verticalArrangement = Arrangement.spacedBy(16.dp), // Dikeyde her öğe arasında 16dp boşluk bırakıyoruz
        modifier = modifier.height(168.dp) // Grid'in yüksekliğini 168dp ile sınırlandırıyoruz
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))

        }

    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicLayoutsCodelabTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

/** 10. Home screen - Scrolling */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Spacer(Modifier.height(16.dp))

        SearchBar(Modifier.padding(horizontal = 16.dp))

        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }

        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    BasicLayoutsCodelabTheme {
        HomeScreen()
    }
}

/** 11. Bottom navigation - Material */
// bazı stil uyarlamaları var. Öncelikle, containerColor parametresini ayarlayarak alt navigasyonun
// arka plan rengini güncelleyebilirsiniz. Bunun için Material temasındaki surfaceVariant rengini kullanabilirsiniz.
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )

            },
            selected = true,
            onClick = {}
        )
    }
}

/** 12. MySoothe App - Scaffold */
//Bu adımda, uygulamanın ana ekranının Scaffold kullanarak nasıl oluşturulacağını ve alt navigasyon çubuğunun nasıl ekleneceğini açıklıyor.
//Scaffold Nedir?
//Scaffold, Material Design temalarını uygulayan bir üst düzey yapı bileşenidir. İçerisinde:
//Üst çubuk (Top bar),
//Alt çubuk (Bottom bar),
//Yüzen buton (Floating Action Button) gibi çeşitli slotlar sağlar.
//Bu örnekte, alt navigasyon çubuğunu (bottom bar) eklemek için kullanacağız.
@Composable
fun MySootheAppPortrait() {
    // Material temasını uyguluyoruz
    BasicLayoutsCodelabTheme {
        //Scaffold: Alt çubuğu ve içeriği bir arada tutar.
        Scaffold(
            // Scaffold'un alt bar kısmına bottom navigation ekliyoruz
            bottomBar = { SootheBottomNavigation() } //SootheBottomNavigation(): Önceden olusturdugumuz  alt navigasyon çubuğu burada kullanılıyor.
        ) { padding ->
            // HomeScreen içerik olarak ekleniyor ve padding ayarlanıyor
            HomeScreen(Modifier.padding(padding))
        }
    }
}

/** 13. Navigation Rail - Material */
// Bu adımda, uygulamamızın yatay modda nasıl görüneceği üzerinde çalışıyorsunuz.
// Yatay modda, alt navigasyon çubuğu yerine ekranın sol tarafında bir Navigation Rail kullanıyoruz.
// Bu, özellikle daha büyük ekranlarda ya da telefon yatay modda olduğunda daha kullanıcı dostu bir arayüz sağlar.

//  1. NavigationRail Nedir?
// NavigationRail, alt navigasyon çubuğuna benzer şekilde çalışır,
// ancak ekranın sol tarafında bir sütun olarak yer alır. İçerisinde navigasyon öğelerini (NavigationRailItem) barındırır.

//  2. SootheNavigationRail Bileşenini Oluşturma:
// Bu bileşen, ana sayfa (Home) ve profil (Profile) seçeneklerini içeren bir navigation rail bileşenidir.
// İki simge ve metin içerir: Spa (Home) ve AccountCircle (Profile).
// İki öğe arasında 8.dp boşluk bırakılır.
// Sütun içeriği dikey olarak ortalanır ve ekranın tamamını kaplar.


@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(), // Yüksekliği doldur
            verticalArrangement = Arrangement.Center, // Dikey ortala
            horizontalAlignment = Alignment.CenterHorizontally // Yatay ortala
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home)
                    )
                },
                selected = true, // Seçili öğe
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp)) // İki simge arasındaki boşluk
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_profile)
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    BasicLayoutsCodelabTheme {
        Surface(color = MaterialTheme.colorScheme.background) { // Arka plan rengini ayarla
            Row {
                SootheNavigationRail() // Sol tarafta navigation rail
                HomeScreen() // Sağ tarafta ana ekran
            }
        }
    }
}
// Surface ve Arka Plan Rengi: Yatay modda Scaffold'un arka plan rengini otomatik ayarladığı gibi, burada da Surface kullanılarak arka plan rengi belirlenir.
// NavigationRailItem: Bu öğeler sol tarafta simgeler ve metinlerle navigasyon öğelerini temsil eder.
// Dikey ve yatay ortalama: Simgeler ve metinler, hem dikey hem de yatay olarak ortalanır, böylece daha temiz bir görünüm elde edilir.

// Bu adımda, uygulamanın yatay modda çalışacak şekilde navigasyon rail'ı ve içerik bileşenlerini düzenleyerek, çok ekranlı ve yönlü bir uygulama deneyimi sağlamış olduk.


/** 14. MySoothe App - Window size */
// Bu adımda, uygulamanızın farklı ekran boyutlarına göre uygun yapılandırmada görünmesini sağlayacaksınız.
// Yani, portre modunda uygulamanın Compact genişliğini, yatay modda ise Expanded genişliği kullanarak doğru düzeni göstereceksiniz.
// Bunu yapmak için calculateWindowSizeClass() fonksiyonunu kullanacağız ve pencere boyutuna göre farklı düzenlemeler yapacağız.

//  1. WindowSizeClass Nedir?
//  WindowSizeClass, ekran boyutlarına göre üç kategoriye ayrılır:
//  Compact: Küçük ekranlar (örneğin, portre modu)
//  Medium: Orta boy ekranlar (bu codelab'de kullanılmayacak)
//  Expanded: Büyük ekranlar (örneğin, yatay mod)

/**
 * Ekran boyutuna göre portre veya yatay düzeni gösteren bir yapı oluşturacağız.
 * Eğer cihaz Compact genişlikteyse portre modunu (MySootheAppPortrait()),
 * Expanded genişlikteyse yatay modunu (MySootheAppLandscape()) göstereceğiz.
 */

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait() // Portre modu için yapı
        }

        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape() // Yatay modu için yapı
        }
        // Medium için herhangi bir düzen kullanmayacağız.
    }
}



