package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.RepoItem

@Composable
fun RepoColumn(
    item: List<RepoItem>
){
    LazyColumn{
        items(item) {
            Box(modifier = Modifier.padding(bottom = 4.dp)) {
               Text(text = it.name)
            }
        }
    }
}