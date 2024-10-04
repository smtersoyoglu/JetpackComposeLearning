package com.smtersoyoglu.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smtersoyoglu.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateCodelabTheme {
                // Temadan gelen 'background' rengini kullanan bir yüzey
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}
// State, bir uygulamada kullanıcıya o anda ne gösterildiğini belirleyen bir durumdur. Yani state dediğimiz şey, uygulamanın o anki görüntüsünü oluşturur.
/** Peki State'ler Nasıl Değişir?
 *  State'ler, bir event (olay) gerçekleştiğinde değişir. Event, uygulamanın içinden ya da dışından tetiklenebilir. Mesela:
 *  - Bir butona tıklamak,
 *  - Bir kutuya bir şeyler yazmak,
 *  - Veya internetten gelen yeni bir bilgi.
 *
 *  Bunlar birer eventtir ve bunlar gerçekleştiğinde state değişir.
 *
 */

/*
@Composable
fun WaterCounter( modifier: Modifier = Modifier) {
    val count = 0
    Text(
        text = "You've had $count glasses.",
        modifier = modifier.padding(16.dp)
    )
}

 */

/** State ve Event iliskisi:
 *  Şöyle düşünebilirsin:
 *  1- State: "Uygulamanın şu an ne gösterdiği." (Mesela: "0 bardak su içtin.")
 *  2- Event: "Bir olayın gerçekleşmesi." (Mesela: "Butona tıkladın ve su içtin.")
 *  3- State Değişimi: Event gerçekleşince state değişir. (Artık ekranda "1 bardak su içtin." yazar.)
 *
 *  Yani bir event olur, bu event state’i değiştirir ve UI'da (kullanıcı arayüzünde) yeni state'i görürüz.
 */
/*
@Composable
fun WaterCounter( modifier: Modifier = Modifier) {
    Column (modifier = modifier.padding(16.dp)) {
        var count = 0
        Text("You've had $count glasses.")
        Button(
            onClick = { count++ },
            Modifier.padding(top = 8.dp)
        ) {
            Text("Add one")
        }
    }
}
 */

/**     State Nasıl Yönetilir?
 * Compose, state'i izlemek için mutableStateOf ve remember gibi yardımcı fonksiyonlar sağlar.
 * Bu fonksiyonlarla state'i güncel tutabilir ve her değişiklikte ekranın doğru kısımlarını yeniden oluşturabiliriz
 */
// Jetpack Compose, state’i hem okuma (read) hem de yazma (write) işlemlerini takip eder.
// Bir state değiştiğinde (örneğin bir butona tıkladığında) sadece bu state’i okuyan composable'lar yeniden çalıştırılır.
// *    State (Durum): Uygulamanın anlık veri durumudur. Örneğin, sayaç değeri.
// *    MutableState: Değiştirilebilir bir state tipidir ve Compose bunu takip eder.
// *    recomposition: State değiştiğinde UI’nın sadece ilgili parçaları yeniden oluşturulur.
// *    mutableStateOf: State’in Compose tarafından takip edilmesini sağlayan fonksiyon.
// *    remember: State’i hatırlamak için kullanılır, böylece her yeniden oluşturma (recomposition) sırasında sıfırlanmaz.

//State değişirse: Compose, sadece state’in okunduğu yerleri yeniden oluşturur (recomposition).
//remember kullanmazsak: State her yeniden oluşturma sırasında sıfırlanır( her yeniden oluşturma sırasında count sıfırlanır.).
// Bu yüzden remember'ı kullanarak state'i kaydediyoruz ve recomposition sırasında korunmasını sağlıyoruz.
/*
@Composable
fun WaterCounter (modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Değiştirilebilir bir state oluşturuluyor, State'i hatırlamak için remember kullanıyoruz
        val count: MutableState<Int> = remember { mutableStateOf(0) } //State Oluşturma (mutableStateOf):
        //        var count by remember { mutableStateOf(0) }  "by" anahtar kelimesi ile delegasyon kullanarak state'i takip ediyoruz
        Text("You've had ${count.value} glasses.")
        // Butona tıklandığında count.value artırılıyor, UI yeniden güncelleniyor
        Button(onClick = { count.value++ }, Modifier.padding(top = 8.dp)) {
            //             count++     by kullanınca .value kullanmamıza gerek kalmiyor.
            Text("Add one")
        }
// Compose'da bir state oluşturmak için genellikle remember { mutableStateOf(0) } kullanırız ve bu state'i güncellemek için .value özelliğine başvururuz.
// Ancak, Kotlin dilinde delegated properties kullanarak bu işlemi daha temiz ve kolay bir hale getirebiliriz.
    }
}
 */
/*
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        if (count > 0) {
            // count > 0 olduğunda bu Text ekranda görünür
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
//State’i Hatırlama: remember kullanarak count adında bir değişken oluşturuyorsun ve bu değişken her butona tıkladığında artıyor.
//Koşullu UI Gösterimi: count > 0 olduğunda, "You've had $count glasses." yazısı ekrana geliyor. Eğer butona hiç tıklanmadıysa bu yazı gösterilmeyecek.
//Recomposition: Butona her tıklanışında count artıyor, ve bu da UI'ın yeniden çizilmesini (recomposition) tetikliyor.
}
 */
/**     Sonuç Olarak:
 * - State-driven UI: Uygulamanın durumu (state) değiştikçe UI otomatik olarak güncellenir. State'i kontrol ederek UI’ın hangi kısmının gösterileceğine ya da butonların nasıl davranacağına karar veriyorsun.
 * - Recomposition: Jetpack Compose, state değiştiğinde yalnızca gerekli olan UI elemanlarını yeniden oluşturur, bu da performansı artırır.
 * - Deklaratif Yaklaşım: UI'ın nasıl görüneceğini ve nasıl davranacağını sadece state'e dayalı olarak belirlediğin için, manuel güncellemelerle uğraşmana gerek kalmaz. Her şey state'e göre otomatik olarak çalışır.
 */
/*
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        if (count > 0) {
            Text("You've had $count glasses.")
        }

        // count 10'a ulaştığında buton pasif olur
        Button(
            onClick = { count++ },
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10 // Buton, count 10’dan küçükken aktif
        ) {
            Text("Add one")
        }
    }
//enabled = count < 10: Buton sadece count 10'dan küçükse tıklanabilir oluyor. Eğer 10’a ulaşırsa, buton pasif hale geliyor.
//count her arttığında, UI otomatik olarak yeniden oluşturuluyor (recomposition), ve butonun aktif olup olmadığını kontrol ediyorsun.
}
 */
/*
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Hem dikey hem de yatay ortalama
    ) {
        WaterCounter()
        StatefulCounter(modifier)
    }
}

 */

/**
 * WaterCounter Fonksiyonu
 * WaterCounter fonksiyonunu güncelleyerek, kullanıcının su içme sayısına göre bir görev göstereceğiz ve bu görevi kapatabilmesini sağlayacağız.
 *
 * Kullanıcı count > 0 olduğunda görev gösterilecek. Bu görevde "15 dakika yürüyüş yaptınız mı?" sorusu olacak.
 * Görev bir Close butonuyla kapatılabilir olacak. Bunun için showTask adında bir state tanımlayacağız. Eğer görev kapatılırsa bu state false olacak ve görev ekranda gösterilmeyecek.
 */

/** Hangi Durumu Ne Zaman Kullanmalı remember - rememberSaveable ?
 * remember: Bileşen yeniden oluşturulsa bile durumu hatırlamak için kullanılır, ancak yapılandırma değişikliklerinde durumu kaybeder.
 * rememberSaveable: Hem yeniden oluşturmalarda hem de yapılandırma değişikliklerinde durumu saklamak için kullanılır.
 */
/*
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        //var count by remember { mutableStateOf(0) }  // Su içme sayısını hatırlamak için

// remember durum kaybolmasına yol acar, Android'in yapılandırma değişikliği (örneğin, cihazın döndürülmesi) sırasında aktiviteyi yeniden oluşturmasından kaynaklanır.
// Durumun Kaybolmasına Yol Açan Diğer Senaryolar, Cihazı döndürme dışında, şu durumlarda da Activity yeniden oluşturulabilir: Dil değiştirme ,Karanlık ve aydınlık modlar arasında geçiş ,Diğer yapılandırma değişiklikleri
// Bu gibi durumlar, sadece remember kullanarak saklanan durumun unutulmasına yol açar.
// remember yalnızca yeniden "composure" sırasında durumu hatırlatır, ancak yapılandırma değişikliği olduğunda (örneğin cihaz döndüğünde) Activity yeniden oluşturulacağı için durum sıfırlanır.
// rememberSaveable Kullanarak Durumun Geri Yüklenmesi; Yukarıda bahsettiğimiz sorunları çözmek için, rememberSaveable kullanılır. rememberSaveable, verileri Bundle içinde saklayarak, yapılandırma değişikliklerinden sonra bile veriyi saklamaya devam eder.
// Bu sayede: Aktivite yeniden oluşturulsa bile (örneğin cihaz döndürme, dil değiştirme vb.), sayaç sıfırlanmaz. rememberSaveable, Bundle içinde saklanabilir tüm değerleri otomatik olarak kaydeder.
        var count by rememberSaveable { mutableStateOf(0) } //// remember yerine rememberSaveable kullanıldı
/** Bu şekilde rememberSaveable kullanarak, cihaz döndürüldüğünde veya diğer yapılandırma değişikliklerinde, su içme sayacı sıfırlanmaz ve kaldığı yerden devam eder.  */

        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }  // Görev gösterilsin mi?

            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },  // Kapatılınca showTask false olacak
                    taskName = "Have you taken your 15 minute walk today?"  // Görev adı
                )
            }
            Text("You've had $count glasses.")  // Kaç bardak içildiğini gösterir
        }

        // Ekleme ve temizleme butonları
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water count")
            }
        }
    }
}
 */

/**
 * Kod Açıklaması:
 * 1- remember ile count ve showTask state'lerini tanımladık:
 *   count su içme sayısını takip ediyor.
 *   showTask görev gösterilsin mi sorusuna yanıt veriyor.
 *
 * 2- WellnessTaskItem composable'ı:
 *   taskName parametresi ile görevin adını alıyor.
 *   onClose parametresi ile kapatma butonuna basıldığında çalışacak fonksiyonu alıyor.
 *   Görev kapatıldığında showTask değişkeni false olacak ve görev artık gösterilmeyecek.
 *
 * 3-Butonlar:
 *   Add one: Su içme sayısını artırır, ama en fazla 10 bardak olabilir.
 *   Clear water count: Su içme sayısını sıfırlar.
 *
 *   remember ve mutableStateOf kullanarak state'leri hafızada tutuyorsun, böylece composable'lar tekrar oluşturuldukça değerler korunuyor.
 */

/*
@Composable
fun WellnessTaskItem(
    taskName: String, // Görev adı
    onClose: () -> Unit, // Kapatma butonuna tıklanınca çalışacak fonksiyon
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }

    }
}

 */

/**     State hoisting
 * State hoisting, bir bileşenin daha basit ve yeniden kullanılabilir olmasını sağlamak için state'i dışarıya taşımak anlamına gelir.
 * Böylece, bileşenler kendi içinde durum tutmaz ve dışarıdan gelen verilere göre davranır.
 * Bu, Jetpack Compose'da uygulamaların daha kolay yönetilmesini, test edilmesini ve hata ayıklanmasını sağlar.
 *
 *   State Hoisting Yaparken Önemli Noktalar:
 * - State, olabildiğince ortak olmayan parent'a yükseltilmelidir (okuma).
 * - State, en çok değiştirilebileceği yere yükseltilmelidir (yazma).
 * - Eğer iki state aynı eventlere tepki olarak değişiyorsa, aynı seviyeye yükseltilmelidirler.
 * */

// Bu örnekte, StatelessCounter, mevcut durumu (count) görüntüler ve bir buton aracılığıyla bu durumu artırır.
// Ancak, durumu yönetmez; bu durum, dışarıdan bir parametre olarak alınır ve olay yönetimi de dışarıdan sağlanır.
@Composable
fun StatelessCounter (count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column (modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

// Bu örnekte, durumu durumsuz composable'dan alarak durumlu composable'a taşıyoruz.
// StatefulCounter, durumu (count) yönetir ve bu durumu StatelessCounter fonksiyonuna iletir.
// StatefulCounter durumu (count) yönetir.
// StatelessCounter sadece bu durumu görüntüler ve butona tıklanınca durumu artırır.
/**      Durum Yükseltme (State Hoisting)
 * Durum yükseltme, bir composable'dan durumu daha yüksek bir seviyeye taşımak ve composable'ı durumsuz hale getirmektir. Böylece durumu ebeveyn composable yönetir.
 *
 * StatefulCounter, durumu yönetir ve StatelessCounter'a durumu iletir.
 * StatelessCounter, durumu sadece görüntüler ve olayları yönetmez.
 *
 *      Durum Yükseltmenin Faydaları;
 * Yeniden Kullanılabilirlik: Artık StatelessCounter, farklı durumlar için yeniden kullanılabilir.
 * Örneğin, hem su hem de meyve suyu sayısını tutmak için StatelessCounter'ı iki kez kullanabilirsiniz.
 * asagidaki durumda StatelessCounter, iki bağımsız durumu yönetmek için iki kez kullanılıyor.
 *
 * Tek Doğruluk Kaynağı: Durum bir yerde yönetildiği için çakışmalar veya tutarsızlıklar önlenir.
 *
 * Daha İyi Performans: Yalnızca gerekli durumu ve olayları ileterek gereksiz yeniden çizimlerden kaçınılır. Compose, yalnızca etkilenen UI parçalarını yeniden oluşturur.
 * */

@Composable
fun StatefulCounter ( modifier: Modifier = Modifier) {
    var waterCount by remember { mutableStateOf(0) }

    var juiceCount by remember { mutableStateOf(0) }

    StatelessCounter(waterCount, { waterCount++ })
    StatelessCounter(juiceCount, { juiceCount++ })
}

// 10. Work with lists

// 1. Liste öğesini güncelleme
// İlk olarak, görevleri temsil eden liste öğesini değiştirmemiz isteniyor. WellnessTaskItem fonksiyonuna bir Checkbox ekleyerek görevin tamamlandığını işaretleyebiliriz.
// Ayrıca, bu öğeyi stateless (durumsuz) hale getirmek için state hoisting uygulamamız gerekiyor.
// Kodda, checkbox’ın işaretlenip işaretlenmediği bilgisi (checked),
// bu durumu değiştirmek için kullanılan geri çağırma fonksiyonu (onCheckedChange), ve öğeyi silmek için bir close butonu ekleniyor.

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

//// 2. Stateful WellnessTaskItem oluşturma
//// Bu adımda, checkedState adında bir değişken tanımlanarak, checkbox'ın işaretli olup olmadığını takip ediyoruz.
//// Bu değişken remember ile saklanıyor, böylece uygulama her yeniden çizildiğinde bu değer hatırlanıyor.
/*
@Composable
fun WellnessTaskItem(
    taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier
) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = onClose,
        modifier = modifier,
    )
}
 */
//  3. Görev modelini oluşturma
// Yeni bir dosya oluşturuluyor: WellnessTask.kt. Bu dosyada her görev için bir ID ve etiket içeren bir data class tanımlanıyor.
// Bu yapı, listedeki her bir görev için benzersiz bir tanımlayıcı ve etiket içerecek.

//data class WellnessTask(val id: Int, val label: String, var checked: Boolean = false)

// 4. Sahte veri üretme
//Görev listesi için 30 adet sahte veri üreten bir fonksiyon oluşturuluyor. Bu fonksiyon, her bir göreve benzersiz bir ID ve etiket atıyor.

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

// 5. Görev listesi oluşturma
//WellnessTasksList fonksiyonu, LazyColumn kullanarak görevleri listeliyor. Bu fonksiyon, görev listesini alıyor ve her bir öğeyi ekrana yerleştiriyor.

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}


//6. WellnessScreen'e liste ekleme
//Görev listesini uygulamanın ana ekranına eklemek için Column kullanılıyor. Bu sayede, önceki sayaç ve yeni görev listesi dikey olarak hizalanıyor.

/**
 *  Compose'un stateleri takip edebilmesi için mutableList değil mutableStateList'i kullanıyoruz. Çünkü bu bir observable type.
 *
 *  Fakat şöyle bir kullanım yapmak performans kaybına sebebiyet verebilir. Çünkü her eleman eklendiğinde recomposition olur.
 *
 *  val list = remember { mutableStateListOf<WellnessTask>()}
 *  list.addAll(getWellnessTasks())
 *
 *  Bunun yerine şunu kullanmalıyız:
 *
 *  val list = remember {mutableStateListOf<WellnessTask>().apply { addAll(getWellnessTasks()) } }
 *
 *
 */
/*
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StatefulCounter()

        val list = remember { getWellnessTasks().toMutableStateList() } // Burada rememberSaveable  kullanılamaz. Uygulama çöker.
        // Sebebi ise rememberSaveable Bundle içinde saklanabilen veri türlerini tutabiliyor. (örneğin, primitive türler, String, Parcelable)
        // Bunu için custom saver kullanabiliriz.

        WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task) })
    }
}
 */

/** -   UI state, sadece ekranda neyin gösterileceğini belirlerken; uygulamanın davranışını kontrol eden iki ana mantık türü vardır:
 * UI Logic Kullanıcı Arayüzü Mantığı) : Stateler değiştiğinde ekranda ne gözükeceğidir. Ekranda durumu nasıl gösterdiğimizle ilgilenir (örn. navigasyon ya da uyarı gösterme).
 *
 * Business Logic (İş Mantığı): State değiştiğinde ne olacağıdır. Durum değişikliklerine nasıl tepki verileceğini belirler (örn. ödeme işlemi yapma veya kullanıcı tercihlerini kaydetme).
 *
 * -   ViewModel'in Rolü
 * * ViewModel, UI state’i yönetir ve business logic'i uygulamanın diğer katmanlarından çağırır.
 * * ViewModel, konfigürasyon değişikliklerinden (örneğin cihaz döndürme) etkilenmez ve UI bileşenlerinin ömründen daha uzun süre yaşar.
 * */

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier.padding(top = 64.dp)) {
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) }
        )
    }
}
//data class WellnessTask(val id: Int, val label: String, val checked: MutableState<Boolean> = mutableStateOf(false))

class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked by mutableStateOf(initialChecked)
}

