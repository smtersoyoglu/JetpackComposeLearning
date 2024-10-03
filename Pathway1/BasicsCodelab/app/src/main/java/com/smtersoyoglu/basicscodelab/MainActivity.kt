package com.smtersoyoglu.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smtersoyoglu.basicscodelab.ui.theme.BasicsCodelabTheme
/** 3. Getting started with Compose  */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // setContent metodu, Compose ile oluşturulan UI'ı ayarlar.

            BasicsCodelabTheme {
                Surface ( //Surface, bir Compose bileşenidir ve UI'ın arka planını belirlemek için kullanılır.
                    // modifier = Modifier.fillMaxSize() ile yüzeyin ekranın tamamını kaplaması sağlanır.
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // yüzeyin rengini uygulamanın temasına göre ayarlar.
                ) {
                    Greeting("Android")
                    // "Android" adını alarak bir selamlama mesajı gösteren Greeting fonksiyonunu çağırır.
                }
            }
        }
    }
}
//@Composable anotasyonu, bu fonksiyonun bir Compose bileşeni olduğunu belirtir.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// @Preview anotasyonu  ile bu composable'ın ön izlemesini görebiliyoruz.
// herhangi bir parametresiz Composable fonksiyonu veya varsayılan parametrelere sahip fonksiyonları
// @Preview ek açıklamasıyla işaretlemeniz ve projenizi derlemeniz yeterlidir.
@Preview(showBackground = true, name = "Text preview")
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greeting("Android")
    }
}
 */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 4. Tweaking the UI (Kullanıcı arayüzünü ayarlama)  */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())

                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // yüzeyin rengini uygulamanın temasına göre ayarlar.
                ) {
                    Greeting("Android")

                }
            }
        }
    }
}

// * Surface içinde bulunan bileşenler bu yüzeyin üstüne çizilir. kodda aslında biz sadece Surface'in rengini değiştiriyoruz ama
// * Text'in rengi de değişti. bunun nedeni;  Surface bileşeni, daha iyi bir kullanıcı deneyimi için metin rengini otomatik olarak ayarlar.
// * Bu sayede, metin arka plan rengine uygun olarak okunabilir olur.
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //Surface ve MaterialTheme, kullanıcı arayüzleri ve deneyimleri oluşturmanıza yardımcı olmak için
    // Google tarafından oluşturulan bir tasarım sistemi olan Material Design ile ilgili kavramlardır.
    Surface (
        color = MaterialTheme.colorScheme.primary,
    ) { // Text'i bir Surface içine alıyoruz. Surface'ın color parametresiyle beraber UI rengini değiştirdik.
        Text(text = "Hello $name!",
            modifier = modifier)
    }
}
 */

// * Modifiers, Compose'da kullanıcı arayüzü (UI) elemanlarının nasıl yerleştirileceğini, görüneceğini veya davranacağını belirlemek için kullanılan parametrelerdir.
// * Basit bir şekilde eklenip, birden fazla modifier bir arada kullanılabilir.
// * Çoğu Compose UI elemanı (örneğin, Surface ve Text), isteğe bağlı olarak bir modifier parametresi kabul eder.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface (
        color = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier.padding(24.dp) ) //Padding Modifier: Bir elemanın etrafına boşluk ekler.
            // padding vererek bu Text'in her yerden 24 dp'lik boşluk bırakmasını sağlıyoruz.
    }
}
 */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 5. Reusing composables (Bileşenlerin yeniden kullanımı)  */
/*
// * Her UI elementini iç içe yazmak kod okunaklığını azaltır.
// * Bu yüzden, UI bileşenlerini küçük composable fonksiyonlarına bölmek ve bu fonksiyonları tekrar tekrar kullanmak daha iyidir.
// * Bu, temiz kod yazma prensiplerine uygundur (Clean code)
// * Best practice olarak bir composable fonksiyonunda default olarak modifier olması iyidir.
// * Daha sonradan başka bir yerde modifiye etmek istediğimizde esneklik sağlar.

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface (
        modifier = modifier ,
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface (
        color = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier.padding(24.dp) )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}
 */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 6. Creating columns and rows (Sütun ve satır oluşturma)  */

// * Compose'daki üç temel standart düzen öğesi şunlardır; Column(Sütun), Row(Satır) ve Box(Kutu)
// * Column, satırları dikey olarak yerleştirir.
// * Row, sütunları yatay olarak yerleştirir.

// * Bunlar, içlerine Composable içerik alabilen Composable fonksiyonlardır,
// * böylece içerisine öğeler yerleştirebilirsiniz. Örneğin, bir Column içindeki her çocuk öğe dikey olarak yerleştirilecektir.

/**
 *  Column {
 *      Text("First row")
 *      Text("Second row")
 *  }
 */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BasicsCodelabTheme {

                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // yüzeyin rengini uygulamanın temasına göre ayarlar.
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column (modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column (modifier = Modifier.weight(1f)) {
                    // * fillMaxWidth, ekranı yatay olarak tamamen kaplar.
                    Text(text = "Hello ")
                    Text(text = name)
                }
                ElevatedButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text("Show more")
                }
            }
        }
    }
}
 */
/**
 * Bu örnekte Column'a 1f weight değeri verildi. Bu yüzden dolayı o kısım 1f'lik bir alan kaplıyor fakat aynı hiyerarşi de başka
 * bir component'in weight değeri olmadığı için 1f oranın tamamı oluyor ve sonra gelen elemanlar direkt sona kayıyor. ElevatedButton'ın
 * weight değeri 5f olsaydı, row'u 6 parçaya ayıracaktı ve 5 birimini bu butona verecekti. Çünkü toplam 6 weight değerinin 5'ine sahip.
 * Bu weight değerinin row ile ilgili olduğunu da unutmayalım. Enine göre büyüme sağlatıyor. fillMaxWidth kullansaydık o column
 * ekranın en olarak tamamını kaplardı ve butona yer kalmazdı. Burada tek bir yerde weight kullandık ve diğer componentleri (burada
 * butonu kastediyoruz) olabildiğince sıkıştırdı.
 */

/** Button ekleme */

// * Buton, material3 paketinde sağlanan bir composable'dır ve son argüman olarak bir composable alır.
// * Trailing lambda'lar parantezlerin dışına taşınabileceği için,
// * butona çocuk olarak herhangi bir içerik ekleyebilirsiniz. Örneğin, bir Text

// * Not: Compose, Material Design Buton spesifikasyonuna göre farklı türde butonlar sağlar
// * Button, ElevatedButton, FilledTonalButton, OutlinedButton ve TextButton.
// * Bu durumda, ElevatedButton kullanacaksınız ve içerik olarak bir Text ile ElevatedButton'ı saracaksınız.


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 7. State in Compose */

// *  Bu bölümde ekranımıza etkileşim ekleyecegiz, kullanıcı degişikliklerine tep vermelerini saglayacagız. butona tıklanınca içeriginin acılması, genisletilmesi gibi
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BasicsCodelabTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // yüzeyin rengini uygulamanın temasına göre ayarlar.
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}
// * Bir composable'a dahili durum eklemek için, mutableStateOf fonksiyonunu kullanabilirsiniz;
// * bu da Compose'un bu durumu okuyan fonksiyonları yeniden oluşturmasını sağlar.
// * Compose'un state'i takip etmesi için mutableStateOf kullanıyoruz
// * Not: State ve MutableState, bazı değerleri tutan ve bu değer değiştiğinde UI güncellemelerini (yeniden kompozisyonları) tetikleyen arayüzlerdir.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//   var expanded = false // Bu şekilde kullanım yapmamalıyız.
//   val expanded = mutableStateOf(false) // Don't do this!
// * Compose'un bunu bir durum değişikliği olarak algılamasını sağlamaz, bu nedenle hiçbir şey olmaz.

    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp // expanded değişkenine göre (butona tıklanma durumunda) extraPadding değişkenini belirliyoruz. (butona tıklanınca 48dp, değilse 0dp)

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier.weight(1f)
                .padding(bottom = extraPadding)
            ) {
                // * fillMaxWidth, ekranı yatay olarak tamamen kaplar.
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}
 */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 8. State hoisting */

// State hoisting yapmak, durumu çoğaltmaktan ve hatalar ortaya çıkarmaktan kaçınır, (Composable fonksiyonlarda stateler birden fazla yerde okunuyorsa veya
// değiştiriliyorsa bunlar, olabildiğince hiyerarşideki en üst yapılara taşınmalı. Bu şekilde bir state'den birden çok tanımlamayız,)
// composable'ların yeniden kullanılmasına yardımcı olur ve composable'ları test etmeyi önemli ölçüde kolaylaştırır.

// Durumun (state) birden fazla composable tarafından kullanılacağı durumlarda, bu durumu ortak bir üst bileşene taşımak,
// kodun tekrarını önler ve yönetimini kolaylaştırır. Ancak, durumu kontrol etmesi gerekmeyen bileşenlerde bu işlem yapılmamalıdır.
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BasicsCodelabTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // yüzeyin rengini uygulamanın temasına göre ayarlar.
                ) {
                    MyApp()
                }
            }
        }
    }
}

/**
 * state hoisting ile Durumu Yukarı Taşıma;
 * State hoisting, birden fazla composable tarafından kullanılan bir durumu (state) ortak bir üst bileşene (ancestor) taşıma işlemidir.
 * Bu, durumu yönetmeyi ve kodun yeniden kullanılabilirliğini artırır.
 */

// * Compose'da UI öğelerini, bir şeyleri gizlemeyiz . Bunun yerine, göstermek istersek onları composable'a ekleriz dahil ederiz,
// * böylece Compose'un oluşturduğu UI ağacına eklenmezler. Bunu basit koşullu Kotlin mantığı ile yaparsınız If else gibi kullanimlarla

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }
    // shouldShowOnboarding = yerine bir by anahtar sözcüğü kullanıyor.
    // Bu, sizi her seferinde .value yazmaktan kurtaran bir özellik temsilcisidir.

    Surface (modifier) {
        if (shouldShowOnBoarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnBoarding = false })

        // MyApp composable fonksiyonunda shouldShowOnboarding durumunu yönetmek için onContinueClicked callback'ini OnboardingScreen fonksiyonuna geçtik.
        // Bu şekilde, butona tıklandığında shouldShowOnboarding false olarak ayarlanır ve giriş ekranı gizlenir.

        } else {
            Greetings()
        }
    }
}

//  OnboardingScreen composable fonksiyonunda oluşturduğunuz durumu MyApp composable fonksiyonuna taşımanız gerekiyor.

@Composable
fun OnboardingScreen(
    // * Higher order fonksiyonlar ile bir state durumunu değiştirdik
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

 */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Greetings()
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }
    val extraPadding = if (expanded) 48.dp else 0.dp // expanded değişkenine göre (butona tıklanma durumunda) extraPadding değişkenini belirliyoruz. (butona tıklanınca 48dp, değilse 0dp)

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                // * fillMaxWidth, ekranı yatay olarak tamamen kaplar.
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/** 9. Creating a performant lazy list */

// Şimdiye kadar Column ile alt altta koymuştuk ama binlerce veri için aşağı kaydırdıkca görüntüleme gibi işlemler için yani xml de ki recyclerview yapısı için
// LazyColumn ve LazyRow kullanıyoruz.

// Not: LazyColumn ve LazyRow, Android Görünümlerindeki RecyclerView ile eşdeğerdir.

/**         LazyColumn ----- RecyclerView Karsilastirilmasi;
 *
 *  LazyColumn, RecyclerView gibi çocuklarını (elemanlarını) geri dönüştürmez (recycle etmez).
 *  RecyclerView, ekrandan çıkan elemanları yeniden kullanır ve yeni elemanlar yaratmak yerine eski elemanları yeniden doldurur.
 *  yani;  ekranda artık görünmeyen bir eleman, bellekte saklanır ve daha sonra tekrar kullanılır.
 *  Yeni bir eleman yaratmak yerine, bu saklanan (geri dönüştürülmüş) elemanlar tekrar doldurularak kullanılır.
 *  Yani, bir eleman ekrandan kaybolduğunda, bu eleman bellekten silinmez, sadece yeni verilerle yeniden doldurulur ve ekranda tekrar gösterilir.
 *  Örneğin recyclerview;
 *  Bir RecyclerView içinde 100 eleman olduğunu varsayalım. Ancak aynı anda ekranda sadece 10 eleman gösteriliyor.
 *  Ekranı kaydırdığınızda, ilk başta ekranda görünen elemanlar ekrandan kaybolur. RecyclerView, bu elemanları bellekten silmez. Bunun yerine, yeni kaydırılan elemanlar için bu elemanları yeniden kullanır ve sadece içindeki veriyi değiştirir.
 *  Bu sayede, her seferinde yeni elemanlar oluşturmak yerine, mevcut elemanlar yeniden doldurulup gösterilir
 *
 *  LazyColumn da ise bu işlem yok bunun yerine her yeni eleman için her seferinde yeni Composable'lar oluşturur. (LazyColumn yeni görünen elemanlar için her seferinde yeni Composable'lar yaratır.)
 *
 *  LazyColumn, ekranı kaydırdığınızda yeni Composable'ları oluşturur (emit eder).
 *  Bu yeni Composable'ları oluşturmak, Android View'leri oluşturmaktan daha ucuz (daha az maliyetli) bir işlemdir.
 *
 *  LazyColumn, her seferinde yeni Composable'lar oluşturmasına rağmen performanslıdır. Çünkü Composable'lar, Android View'ler kadar kaynak tüketmez.
 *
 *  Özetle, LazyColumn her kaydırma işleminde yeni elemanlar oluşturur ama bu işlem performans açısından sorun yaratmaz
 *  Çünkü Composable'lar, View'lere göre daha hafif ve hızlı oluşturulabilir. Composable'lar View'lardan daha hafif olduğu için daha performanslıdır diyebiliriz.
 *
 *
 * */


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names : List<String> = List(1000) {" $it"}
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 10. Persisting state */
// Bu bölüm, Jetpack Compose'da durumun nasıl kalıcı hale getirileceğini anlatıyor.

/**
 * 1- Sorunlar;
 *  - Onboarding Ekran Durumunun Kalıcı Olması: Uygulamayı açtığımızda ve butonlara tıkladıgımızda, cihazı döndürdügümüzde (örnegin yataydan dikeye) onboarding ekranı tekrar
 *  gösterilir. Bunun nedeni, 'remember' fonksiyonunun sadece composable'ın Composition'da kaldıgı sürece durumunu hatırlaması. Aktivite yeniden baslatıldıgında tüm durum kaybolur.
 *
 *  - Liste Öğelerinin Genişleme Durumunun Kalıcı Olması: Bir liste öğesini genişletip listeyi kaydırarak veya cihazı döndürerek öğeyi görünümden çıkarırsanız, öğe tekrar ilk durumuna döner.
 *
 * 2- Cözümleri;
 *   -'remember' yerine 'rememberSaveable' kullanarak durumu kalıcı hale getirebiliriz. 'rememberSaveable', durumun yapılandırma degisiklikleri(örnegin döndürme) ve
 *   islem ölümü(process death) sırasında korunmasını saglar.
 *
 *
 *  -- Onboarding Ekranının Durumunu Kalıcı Hale Getirme:
 *           var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
 * Bu kod, onboarding ekranının durumu (gösterilip gösterilmediği) kalıcı hale getirir. Uygulamayı çalıştırın, cihazı döndürün, karanlık moda geçin veya işlemi sonlandırın.
 * Onboarding ekranı, uygulamadan çıkmadığınız sürece tekrar gösterilmez.
 *
 *
 *  -- Liste Öğelerinin Genişleme Durumunu Kalıcı Hale Getirme:
 *           var expanded by rememberSaveable { mutableStateOf(false) }
 * Bu kod, liste öğelerinin genişleme durumunu kalıcı hale getirir. Bir liste öğesini genişletip kaydırın veya cihazı döndürün, öğenin durumu korunur.
 *
 */


 */

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 11. Animating your list */

/**
 * Jetpack Compose, UI animasyonları için çeşitli API'ler sunar. Basit animasyonlardan karmaşık geçişlere kadar farklı seviyelerde kontrol sağlayan yöntemler vardır.
 * Bu bölümde, düşük seviyeli bir API olan animateDpAsState kullanılarak, boyut değişikliği animasyonu yapılacak.
 *
 * animateDpAsState Kullanımı:
 * animateDpAsState, bir State nesnesi döndürür ve hedef değere (target value) doğru animasyonu gerçekleştirir. Bu hedef değer Dp türündedir.
 *
 */

/** *
 * Farklı animasyon türlerini keşfetmek için spring, tween, repeatable gibi farklı animationSpec parametrelerini deneyebilirsiniz.
 * Ayrıca, animateColorAsState gibi farklı türde animasyonlar da yapabilirsiniz.
 */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Greetings()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }


}

@Composable
fun OnboardingScreen(
    // * Higher order fonksiyonlar ile bir state durumunu değiştirdik
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }

}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { " $it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

// Animasyonlar da padding'e negatif değer verirsek uygulama crash olur.
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
            // yaylı animasyon
        )
    )

    /*
    val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp) //  expanded durumu true ise extraPadding değeri 48.dp, değilse 0.dp olacak şekilde animasyon yapılır.
// *  Animasyon için animateDpAsState composable fonksiyonunu kullanıyoruz. Bu, animasyon bitene kadar değeri
// * animasyon tarafından sürekli güncellenecek bir State nesnesi döndürür. Türü Dp olan bir "hedef değer" alır.

     */
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                // * fillMaxWidth, ekranı yatay olarak tamamen kaplar.
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

 */


/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 12. Styling and theming your app ( Uygulamanızı şekillendirme ve temalandırma) */

// Bu bölüm, Jetpack Compose'da uygulamanızın stilini ve temasını nasıl özelleştireceğinizi anlatıyor.
// Uygulamanızda herhangi bir özel stil tanımlamadan bile, Compose varsayılan bir tema ve karanlık mod desteği sağlar.
// BasicsCodelabTheme, Compose'da varsayılan olarak kullanılan bir tema bileşeni. İçerisinde MaterialTheme kullanılıyor, bu da uygulamanızdaki tüm bileşenlere Material Design stil ilkelerini uyguluyor.

/**
 * MaterialTheme Nedir?
 * MaterialTheme, Material Design stil ilkelerini uygulayan bir composable fonksiyon.
 * İçerisinde renk şeması (colorScheme), tipografi (typography) ve şekiller (shapes) gibi stil bilgilerini barındırır.
 */

/**
 *  Stil Özelleştirme:
 * Herhangi bir bileşenin stilini değiştirmek için MaterialTheme'dan alınan stilleri kullanabilirsiniz. Örneğin, bir Text bileşenine headlineMedium stilini uygulayabilirsiniz:
 *
 * Text(text = name, style = MaterialTheme.typography.headlineMedium)
 */

/**
 * Stilin Kopyalanması ve Özelleştirilmesi:
 * Bazen mevcut bir stili tamamen değiştirmek yerine, üzerinde küçük değişiklikler yapmak isteyebilirsiniz. Bu durumda, copy fonksiyonunu kullanarak mevcut bir stili kopyalayabilir ve üzerinde değişiklik yapabilirsiniz.
 * Örneğin, bir metnin kalınlık (fontWeight) değerini ExtraBold yapabilirsiniz:
 *
 * Text(
 *     text = name,
 *     style = MaterialTheme.typography.headlineMedium.copy(
 *         fontWeight = FontWeight.ExtraBold
 *     )
 * )
 */

/**
 * Karanlık Mod Önizlemesi Ayarlama:
 * Varsayılan olarak, Compose önizleme penceresi uygulamanızı yalnızca aydınlık modda gösterir. Ancak, karanlık modu da önizlemede görmek için @Preview anotasyonuna uiMode = UI_MODE_NIGHT_YES
 * ekleyebiliriz.
 */

/**
 * Temayı Özelleştirme:
 * Temanızı özelleştirmek için ui/theme/Theme.kt dosyasını düzenleyebilirsiniz. Örneğin, yeni renkler tanımlayabilir ve bunları tema paletinize atayabilirsiniz:
 *
 * val Navy = Color(0xFF073042)
 * val Blue = Color(0xFF4285F4)
 * val LightBlue = Color(0xFFD7EFFE)
 * val Chartreuse = Color(0xFFEFF7CF)
 *
 * private val LightColorScheme = lightColorScheme(
 *     surface = Blue,
 *     onSurface = Color.White,
 *     primary = LightBlue,
 *     onPrimary = Navy
 * )
 * Bu renkler uygulamanızda kullanılacak ve karanlık mod için farklı bir renk paleti tanımlayabilirsiniz:
 *
 * private val DarkColorScheme = darkColorScheme(
 *     surface = Blue,
 *     onSurface = Navy,
 *     primary = Navy,
 *     onPrimary = Chartreuse
 * )
 */

/**     Tüm bunları Theme.kt da yaptık.
 * Uygulamanızı çalıştırdığınızda, hem aydınlık hem de karanlık mod için tanımladığınız yeni renkleri ve stilleri görebilirsiniz.
 * MaterialTheme ile çalışarak uygulamanızın temasını merkezi bir yerden kontrol edebilir, farklı modlara uyumlu hale getirebilirsiniz.
 * Özet: Jetpack Compose'da stil ve tema yönetimi, uygulamanızın genel görünümünü ve hissiyatını kontrol etmenizi sağlar. MaterialTheme,
 * hem aydınlık hem de karanlık modda tutarlı bir kullanıcı deneyimi sağlamak için güçlü bir araçtır. Mevcut stilleri kopyalayarak küçük değişiklikler yapabilir veya tamamen özelleştirilmiş bir tema oluşturabilirsiniz.
 */
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Column {
                    Text(text = "Hello",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.tertiary)

                    Text(
                        text = "Android",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

            }
        }
    }
}

 */

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/** 13. Finishing touches! */

//Butonu İkonla Değiştirin:
//IconButton composable'ını kullanarak butonu bir ikonla değiştirin.
//Icons.Filled.ExpandLess ve Icons.Filled.ExpandMore ikonlarını kullanmak için material-icons-extended bağımlılığını build.gradle.kts dosyanıza ekleyin:
//  implementation("androidx.compose.material:material-icons-extended")

//Bu kod, bir LazyColumn içinde yer alan kartların genişletilip daraltılmasını sağlar ve her kartın içeriklerini animasyonla gösterir.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    /**
     * var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }:
     * Uygulamanın başında gösterilecek olan giriş ekranının (onboarding) gösterilip gösterilmediğini kontrol eden bir durum (state) değişkenidir.
     * rememberSaveable, bu durumun cihaz döndürüldüğünde veya yapılandırma değiştiğinde korunmasını sağlar.
     */

    Surface(modifier) { // Surface: UI'nız için bir zemin oluşturur. Arka plan rengi ve diğer stil özellikleri buraya eklenebilir.
        if (shouldShowOnBoarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnBoarding = false }) //if (shouldShowOnBoarding): Eğer shouldShowOnBoarding true ise OnboardingScreen composable'ı gösterilir;
            // false ise Greetings composable'ı gösterilir.
        } else {
            Greetings()
        }
    }

}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) { //LazyColumn: Çok sayıda öğe içerdiğinde performansı artıran bir liste bileşeni. Ekranda görünen öğeleri dinamik olarak oluşturur.
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card( //Card: Kart görünümü sağlar, genellikle gruplandırılmış içeriklerin gösterilmesi için kullanılır.
        colors = CardDefaults.cardColors( //CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary): Kartın arka plan rengini ayarlar. Burada, uygulamanın temasından birincil renk kullanılmış.
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name) //CardContent(name): Kartın içeriğini belirler, burada name parametresini kullanarak içeriği doldurur.
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    //var expanded by rememberSaveable { mutableStateOf(false) }: Bu, kartın genişleyip genişlemediğini kontrol eden bir durum değişkenidir.

    Row( // Row: Childlarını(icerigi) yatay olarak hizalayan bir layout. Yani, Column ve IconButton yan yana sıralanır.
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(  //animateContentSize: İçeriğin boyutu değiştiğinde animasyon uygulayan bir modifier.
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) { //IconButton: Bir ikonlu buton. Tıklandığında expanded durumunu değiştirir.
            Icon( //Icon: Görsel bir ikon. Genişletme durumuna göre (expanded) iki farklı ikon gösterir: ExpandLess (kapatmak için) veya ExpandMore (açmak için).
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "Show Less"
                } else {
                    "Show More"
                }
            )
        }
    }
}