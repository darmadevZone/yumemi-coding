package jp.co.yumemi.android.code_check.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import jp.co.yumemi.android.code_check.ViewModel.RepoViewModel

@Composable
fun repoScreen(
    name: String,
) {
    Column() {
        Text(text = "repoScrenn")
        Text(text = name.toString())
    }
}