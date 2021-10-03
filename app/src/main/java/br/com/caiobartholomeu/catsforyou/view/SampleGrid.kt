package br.com.caiobartholomeu.catsforyou.view

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.caiobartholomeu.catsforyou.model.SampleData
import br.com.caiobartholomeu.catsforyou.retrofit.RetrofitInitializer
import br.com.caiobartholomeu.catsforyou.retrofit.service.NoteService
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit
import java.net.HttpURLConnection
import java.net.URL

@ExperimentalFoundationApi
@Composable
fun SampleGrid() {

/////////////////////////////
    val url = URL("https://api.imgur.com/3/gallery/search/?q=cats")
    val conn = url.openConnection() as HttpURLConnection
    conn.apply {
        addRequestProperty("client_id", "1ceddedc03a5d71")
        addRequestProperty("client_secret", "63775118a9f912fd91ed99574becf3b375d9")
        setRequestProperty("Authorization", "OAuth 2")
    }
////////////////////////////





    val context = LocalContext.current

    val dataFileString = getJsonDataFromAsset(context, "SampleData.json")
///////////////////////////////////////////////

    val call = RetrofitInitializer().noteService().list()
    call.enqueue(object : Callback<List<ContactsContract.CommonDataKinds.Note>?> {
        override fun onResponse(
            response: Response<List<ContactsContract.CommonDataKinds.Note>?>?,
            retrofit: Retrofit?
        ) {
            response?.body()?.let {
                val notes: List<ContactsContract.CommonDataKinds.Note>
            }
        }

        override fun onFailure(t: Throwable?) {
            t?.message?.let { Log.e("onFailure error", it) }
        }
    })
///////////////////////////////////////////////

    val gson = Gson()
    val gridSampleType = object : TypeToken<List<SampleData>>() {}.type
    val sampleData: List<SampleData> = gson.fromJson(dataFileString, gridSampleType)

    Column(
       modifier = Modifier
           .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            modifier = Modifier
                .padding(5.dp)
        ) {
            items(sampleData) { data ->
                SampleDataGridItem(data)
            }
        }
    }
}

@Composable
fun SampleDataGridItem(data: SampleData) {
    Card(

        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        elevation = 1.dp,
        shape = RoundedCornerShape(5.dp)


    )
    {
        Column(
            modifier = Modifier.padding(9.dp)
        ) {
            Image(
                painter = rememberImagePainter(data.link),
                contentDescription = "Grid Image",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

fun getJsonDataFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}











