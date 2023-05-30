package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.RepoItem
import jp.co.yumemi.android.code_check.ViewModel.RepoViewModel

@Composable
fun RepoColumn(
    items: List<RepoItem>,
    viewModel: RepoViewModel = RepoViewModel(),
    naviToRepoScreen: (RepoItem) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            repoItem(item = item, onClick = {
                naviToRepoScreen(item)
//                viewModel.getRepoData(item.owner, item.owner.repos_url)
            })
        }
    }
}

@Composable
fun repoItem(item: RepoItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = item.name,
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}