package jp.co.yumemi.android.code_check.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    if (repoItem == null) {
        CircularProgressIndicator()
        return
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(model = repoItem!!.owner.avatar_url, contentDescription = "")
        Text(text = repoItem!!.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                Text(text ="written in ${repoItem!!.language}")
            }
            Column {
                Text(text = "${repoItem!!.stargazersCount} stars")
                Text(text = "${repoItem!!.watchersCount} watchers")
                Text(text = "${repoItem!!.forksCount} forks")
                Text(text = "${repoItem!!.openIssuesCount} open issues")
            }
        }
    }
}