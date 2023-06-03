package jp.co.yumemi.android.code_check.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import jp.co.yumemi.android.code_check.R

@Composable
fun TopBar() {
    TopAppBar() {
        Text(text = stringResource(id = R.string.app_name))
    }
}
