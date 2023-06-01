package jp.co.yumemi.android.code_check.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.co.yumemi.android.code_check.ViewModel.SearchDetailViewModel


@Composable
fun repoScreen(
    id: Int
) {
    val searchDetailViewModel = viewModel<SearchDetailViewModel>()
    val repoItem by searchDetailViewModel.repoItem.collectAsState()
    LaunchedEffect(Unit) {
        searchDetailViewModel.getRepoItemData(id)
    }
    if(repoItem == null){
        CircularProgressIndicator()
        return
    }
    Column {
        Text(text = repoItem!!.name)
    }
}