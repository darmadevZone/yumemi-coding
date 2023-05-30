/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.co.yumemi.android.code_check.ViewModel.RepoViewModel
import jp.co.yumemi.android.code_check.components.RepoColumn
import jp.co.yumemi.android.code_check.components.SearchBar
import jp.co.yumemi.android.code_check.components.TopBar
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
            val SearchViewModel by viewModels<SearchViewModel>()
            val searchRepo by SearchViewModel.searchQuery.collectAsState()
            val repoList by SearchViewModel.repoList.collectAsState()
            NavHost(navController = navController, startDestination = "MainScreen") {
                composable(route = "MainScreen") {
                    Scaffold(topBar = { TopBar() }) {
                        Column() {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(4.dp)
                            ) {
                                SearchBar(
                                    modifier = Modifier
                                        .padding(it)
                                        .fillMaxWidth(),
                                    value = searchRepo,
                                    onChange = { SearchViewModel.changeQuery(it) },
                                    onSearch = { SearchViewModel.searchRepo() }
                                )
                            }
                            if (repoList.isNotEmpty())
                                RepoColumn(
                                    items = repoList,
                                    naviToRepoScreen = {
                                        navController.navigate("RepoScreen/${it.name}")
                                    },
                                )
                        }
                    }
                }
                composable(
                    route = "RepoScreen/{repos_url}/{name}",
                    arguments = listOf(
                        navArgument(name = "name") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    val name = it.arguments!!.getString("name")!!
                    repoScreen(name = name)
                }
            }
        }
    }
}
