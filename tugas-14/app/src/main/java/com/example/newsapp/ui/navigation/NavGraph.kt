package com.example.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.DetailScreen
import com.example.newsapp.ui.screens.HomeScreen
import com.example.newsapp.ui.screens.WebViewScreen
import com.example.newsapp.ui.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val viewModel: NewsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel
            ) { article ->
                viewModel.selectArticle(article)
                navController.navigate("detail")
            }
        }
        composable("detail") {
            val articleState = viewModel.selectedArticle.value
            articleState?.let { article ->
                DetailScreen(
                    article = article,
                    onBackClick = { navController.popBackStack() },
                    onReadFullArticle = { url ->
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        navController.navigate("webview/$encodedUrl")
                    }
                )
            }
        }
        composable(
            route = "webview/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(
                url = url,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
