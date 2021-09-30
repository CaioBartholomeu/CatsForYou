package br.com.caiobartholomeu.catsforyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//RESOLVER
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import br.com.caiobartholomeu.catsforyou.ui.theme.CatsForYouTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsForYouTheme {
                NavigatePage()
                }
            }
        }
    }

@ExperimentalFoundationApi
@Composable
fun NavigatePage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = "sample_data"
    ) {
        composable("sample_data") {
            SampleGrid(navHostController)
        }
        composable("sample_grid_detail/{item}",
            arguments = listOf(
                //RESOLVER
                //navArgument("item") {
                //    type = NavType.StringType
                //}
            )
        )
        { navBackStackEntry ->
            navBackStackEntry?.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, SampleData::class.java)
                SampleDataDetails(data = item)
            }
        }
    }
}
