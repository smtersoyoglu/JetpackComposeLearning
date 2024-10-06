package com.smtersoyoglu.themingcodelab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.smtersoyoglu.themingcodelab.ui.theme.ThemingCodelabTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemingCodelabTheme {
                //ImageBorderAnimation()
                Surface(color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(top = 48.dp)) {
                    //TopicList()
                    //TabIndicatorDemo()
                    //LoadingRow()
                    SwipeToDismissSample()
                }
            }
        }
    }
}

// $ git clone https://github.com/android/codelab-android-compose
// https://developer.android.com/courses/pathways/jetpack-compose-for-android-developers-2?authuser=1#codelab-https://developer.android.com/codelabs/jetpack-compose-theming
/*
@Composable
fun ImageBorderAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing) // Animasyon süresi
        ), label = ""
    )

    // Gökkuşağı renk geçişi için brush
    val rainbowColorsBrush = Brush.sweepGradient(
        listOf(
            Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red
        )
    )

    val borderWidth = 8.dp
    val imageSize = 100.dp // Resmin boyutu

    Box(
        modifier = Modifier.fillMaxSize(), // Ekranın tamamını kaplayan bir Box
        contentAlignment = Alignment.Center // İçerikleri ekranın ortasına hizala
    ) {
        Image(
            painter = painterResource(id = R.drawable.duck), // Resmini buraya ekle
            contentDescription = null,
            modifier = Modifier
                .size(imageSize) // Resmin boyutunu ayarlıyoruz
                .clip(CircleShape) // Resmi yuvarlak şekil içinde tutuyoruz
                .drawBehind {
                    rotate(rotationAnimation.value) {
                        drawCircle(
                            rainbowColorsBrush,
                            style = Stroke(borderWidth.toPx()) // Dönen çerçeve
                        )
                    }
                }
                .padding(borderWidth) // Çerçeve ile resim arasına boşluk bırakıyoruz
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageBorderAnimationPreview() {
    ThemingCodelabTheme {
        ImageBorderAnimation()
    }
}
 */


/**     animateContentSize özelliği; Jetpack Compose'da bir Composable'ın boyutu değiştiğinde otomatik olarak bir animasyon uygulayan bir modifier'dır a */
/*
@Composable
fun TopicList() {
    val topics = listOf(
        "Topic 1" to "This is the description text for Topic 1. Click to see more details.",
        "Topic 2" to "This is the description text for Topic 2. You can see the full content.",
        "Topic 3" to "This is the description text for Topic 3. Click to expand the content."
    )

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        topics.forEach { (title, body) ->
            TopicRow(title, body)
            HorizontalDivider()
        }
    }
}

@Composable
fun TopicRow (title: String, body: String) {

    // içeriğin genişleyip daralması için gerekli olan state
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize(animationSpec = tween(300)) // Animasyon ayarı
            .clickable { isExpanded = !isExpanded } // Tıklayınca içeriği genişletip daraltır
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = body, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**     animateContentSize;
 * animateContentSize özelliği, Jetpack Compose'da bir bileşenin (örneğin, Column) içeriği değiştiğinde, boyutunun yumuşak bir şekilde animasyonla değişmesini sağlar.
 * İçerik genişlediğinde veya daraldığında bu geçişi daha hoş bir hale getirmek için kullanılır.
 *
 * Animasyonu daha da özelleştirmek için animationSpec parametresi ile farklı animasyon türleri ekleyebilirsin.
 * Örneğin, animasyonun süresini kontrol etmek için tween() fonksiyonunu kullanabilirsin.
 */

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemingCodelabTheme {
        TopicList()
    }
}
 */

/** 6. Animating multiple values */
/**
 * Transition API ile birden fazla animasyon değeri aynı anda kontrol edilebilir. Sekme geçişi sırasında, göstergenin sol ve sağ kenarlarının pozisyonları (horizontal position) ve rengi animasyonlu olarak değiştirilecektir.
 * updateTransition(): Hangi sekmenin seçili olduğunu belirleyerek animasyonların hedef durumunu (target state) belirler.
 * animateDp(): Sekme göstergesinin sol ve sağ kenarlarının yatay pozisyonlarını animasyonlar.
 * animateColor(): Sekme göstergesinin rengini animasyonlar.
 */
/*
// Tablar için iki farklı durum (Home ve Work)
enum class TabPage { Home, Work }

// Renk tanımları
val PaleDogwood = Color(0xFFEAC4C4)
val Green = Color(0xFF88C399)
@Composable
fun TabIndicatorDemo() {
    // Seçilen tab sayfasını tutmak için bir durum
    var selectedTab by remember { mutableStateOf(TabPage.Home) }

    // Tabların pozisyonlarını tanımlama (örnek pozisyonlar)
    val tabPositions = listOf(
        TabPosition(0.dp, 100.dp),  // Home tab'ı pozisyonu
        TabPosition(100.dp, 200.dp) // Work tab'ı pozisyonu
    )

    // Transition kullanarak animasyonu yönetme
    val transition = updateTransition(selectedTab, label = "Tab indicator")

    // Göstergenin sol ve sağ pozisyonu için animasyon
    val indicatorLeft by transition.animateDp(label = "Indicator left") { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(label = "Indicator right") { page ->
        tabPositions[page.ordinal].right
    }

    // Tab arka plan rengi için animasyon
    val backgroundColor by transition.animateColor(label = "Background color") { page ->
        if (page == TabPage.Home) PaleDogwood else Green
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Arka plan rengini uygulama
            .padding(16.dp)
    ) {
        // TabRow içindeki tablar
        TabRow(selectedTabIndex = selectedTab.ordinal) {
            Tab(
                selected = selectedTab == TabPage.Home,
                onClick = { selectedTab = TabPage.Home }
            ) {
                Text("Home")
            }
            Tab(
                selected = selectedTab == TabPage.Work,
                onClick = { selectedTab = TabPage.Work }
            ) {
                Text("Work")
            }
        }

        // Göstergenin konumunu ve boyutunu ayarlama
        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .offset(x = indicatorLeft) // Göstergenin sol pozisyonu
                .background(Color.Black) // Göstergenin rengi
        )
    }
}

// Tab pozisyonlarını tanımlayan bir veri sınıfı
data class TabPosition(val left: Dp, val right: Dp)

// Önizleme için bir fonksiyon
@Preview(showBackground = true)
@Composable
fun PreviewTabIndicatorDemo() {
    ThemingCodelabTheme  {
        TabIndicatorDemo()
    }
}
 */
/** 7. Repeating animations "tekrarlayan animasyonlar"-- Loading örnegi */
/*
@Composable
fun LoadingRow() {
    // Sonsuz bir geçiş oluşturmak için rememberInfiniteTransition kullanıyoruz.
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // alfa değerini 0'dan 1'e animasyonlu olarak değiştiriyoruz.
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,  // Başlangıç değeri
        targetValue = 1f,   // Hedef değer
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000  // Animasyon süresi
                0.7f at 500  // 500ms'de alfa 0.7 olacak
            },
            repeatMode = RepeatMode.Reverse // Animasyonun tersine dönecek şekilde ayarlanması
        ),
        label = "alpha"  // Animasyon için etiket
    )

    // Yükleme göstergesinin görünümünü oluşturuyoruz.
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Gri bir daire ve bir çubuk gösteriyoruz.
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = Color.Gray.copy(alpha = alpha), // alfa değeri ile rengi değiştiriyoruz
            strokeWidth = 4.dp
        )

        // Ayrıca bir çubuk gösterebiliriz, örneğin bir yükleme çubuğu.
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth().height(4.dp),
            color = Color.Gray.copy(alpha = alpha) // alfa değeri ile rengi değiştiriyoruz
        )
    }
}
 */

/** 8. Gesture animation (Dokunma Animasyonu) */

/**
 * Bu bölümde, dokunma girdilerine dayalı olarak animasyonları nasıl çalıştıracağımızı öğreneceğiz.
 * Kendi swipeToDismiss (kaydırarak silme) modifier'ımızı sıfırdan oluşturacağız.
 *
 * Not: SwipeToDismiss, Material'da kullanabileceğiniz bir Composable'dır ve kendi özel modifier'ınızı uygulamak zorunda kalmadan kullanılabilir.
 */

@Composable
fun SwipeToDismissSample() {
    // Ekranda gösterilecek item'in dismissed olup olmadığını takip eden state
    var dismissed by remember { mutableStateOf(false) }

    // Eğer dismissed değilse öğeyi göster
    if (!dismissed) {
        // swipeToDismiss animasyonunu uygulayan öğe
        SwipeToDismissBox(
            onDismissed = { dismissed = true }
        )
    } else {
        // Eğer dismissed ise, ekrana bir mesaj göster
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BasicText(text = "Öğe kaydırılarak silindi!")
        }
    }
}

@Composable
fun SwipeToDismissBox(onDismissed: () -> Unit) {
    // Öğenin yatay konumunu takip eden animatable
    val offsetX = remember { Animatable(0f) }

    // Animasyonları yönetmek için coroutine scope
    val coroutineScope = rememberCoroutineScope()
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary)
            // swipeToDismiss animasyonunu burada uyguluyoruz
            .pointerInput(Unit) {
                // Hız hesaplama ve kaydırma animasyonu için gerekli olan decay fonksiyonu
                val decay = splineBasedDecay<Float>(this)
                coroutineScope.launch {
                    while (true) {
                        // İlk dokunma olayını bekle
                        val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                        offsetX.stop() // Herhangi bir devam eden animasyonu durdur

                        // VelocityTracker: Kullanıcının kaydırma hızını ölçmek için kullanılır
                        val velocityTracker = VelocityTracker()

                        // Sürükleme olaylarını yakala
                        awaitPointerEventScope {
                            horizontalDrag(pointerId) { change ->
                                val horizontalDragOffset = offsetX.value + change.positionChange().x
                                launch {
                                    // Sürükleme miktarına göre ofseti güncelle
                                    offsetX.snapTo(horizontalDragOffset)
                                }

                                // Hız hesaplamak için konumu kaydediyoruz
                                velocityTracker.addPosition(change.uptimeMillis, change.position)

                                change.consume() // Olayı işaretle
                            }
                        }

                        // Sürükleme bitti, hızını hesapla
                        val velocity = velocityTracker.calculateVelocity().x
                        val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)

                        // Eğer öğe ekran dışına yeterince kaydırıldıysa kaldır
                        if (targetOffsetX.absoluteValue > size.width) {
                            onDismissed()
                        } else {
                            // Yeterince hızlı kaydırılmadıysa eski pozisyona dön
                            offsetX.animateTo(0f)
                        }
                    }
                }
            }
            // Ofset'i öğenin pozisyonuna uygula
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
    ) {
        // Kutunun içeriği
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BasicText(text = "Kaydırarak sil!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSwipeToDismissExample() {
    SwipeToDismissSample()
}










