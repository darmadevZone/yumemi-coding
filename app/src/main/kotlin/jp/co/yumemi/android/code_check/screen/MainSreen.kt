package jp.co.yumemi.android.code_check.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jp.co.yumemi.android.code_check.SearchViewModel
import jp.co.yumemi.android.code_check.components.RepoColumn
import jp.co.yumemi.android.code_check.components.SearchBar
import jp.co.yumemi.android.code_check.components.TopBar

@Composable
fun MainScreen(
    navController:NavController
) {
    val ViewModel = viewModel<SearchViewModel>()
    val searchRepo by ViewModel.searchQuery.collectAsState()
    val repoList by ViewModel.repoList.collectAsState()
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
                    onChange = { ViewModel.changeQuery(it) },
                    onSearch = { ViewModel.getRepoData() }
                )
            }
            if (repoList.isNotEmpty())
                RepoColumn(
                    items = repoList,
                    naviToRepoScreen = {
                        navController.navigate("RepoScreen/${it.id}")
                    },
                )
        }
    }
}