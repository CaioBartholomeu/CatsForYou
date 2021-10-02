package br.com.caiobartholomeu.catsforyou.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//RESOLVER
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.caiobartholomeu.catsforyou.view.SampleGrid
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
            SampleGrid()
        }
    }
}
