package com.herpestes.datastore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.herpestes.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {

    val context = LocalContext.current
    val ads = AppDatastore(context)

    LaunchedEffect(key1 = true){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            ads.kayitAd("Ahmet")
            ads.kayitYas(23)
            ads.kayitBoy(1.87)
            ads.kayitBekarMi(true)

            val liste = HashSet<String>()
            liste.add("Mehmet")
            liste.add("Zeynep")

            ads.kayitArkadasListe(liste)

            //okuma islemleri
            val gelenAd = ads.okuAd()
            val gelenYas = ads.okuYas()
            val gelenBoy = ads.okuBoy()
            val gelenBekarMi = ads.okuBekarMi()
            val gelenListe = ads.okuArkadasListe()
            Log.e("Gelen ad", gelenAd)
            Log.e("gelenYas", gelenYas.toString())
            Log.e("gelenBoy", gelenBoy.toString())
            Log.e("gelenBekarMi", gelenBekarMi.toString())

            for(a in gelenListe!!){
                Log.e("Gelen Arkadaş", a)
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStoreTheme {
        Sayfa()
    }
}