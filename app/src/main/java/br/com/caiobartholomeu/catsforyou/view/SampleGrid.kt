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

@ExperimentalFoundationApi
@Composable
fun SampleGrid() {
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
            /*
            .clickable {
                val itemVal = Gson().toJson(data)
                navController.navigate("sample_grid_detail/$itemVal")
            }
                */
            .padding(5.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)


    )
    {
        Column(
            modifier = Modifier.padding(9.dp)
        ) {
            Image(
                //painter = rememberImagePainter("https://i.imgur.com/lK4AcJ1.jpg"),
                painter = rememberImagePainter(data.link),
                contentDescription = "Grid Image",
                modifier = Modifier.size(128.dp)
                /*
                painterResource(R.drawable.mie_img),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(1.dp)
                    .clip(RoundedCornerShape(5.dp))
                    */
            )

            Spacer(modifier = Modifier.padding(18.dp))
        }
    }
}

fun getJsonDataFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}











