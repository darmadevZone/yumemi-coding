/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.co.yumemi.android.code_check.screen.MainScreen
import jp.co.yumemi.android.code_check.screen.repoScreen
import java.util.*

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var lastSearchDate: Date
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "MainScreen") {
                composable(route = "MainScreen") {
                    MainScreen(navController = navController)
                }
                composable(
                    route = "RepoScreen/{id}",
                    arguments = listOf(
                        navArgument(name = "id") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val id = it.arguments!!.getInt("id")!!
                    repoScreen(id)
                }
            }
        }
    }
}
