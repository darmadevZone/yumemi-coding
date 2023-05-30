package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.helper.widget.MotionPlaceholder


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    placeholder: String = "Githubのリポジトリが検索できるよ...",
    onSearch: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(),
        shape = RepoTextFieldedShape,
        placeholder = { Text(text = placeholder, color = Color.Black.copy(alpha = 0.5f)) },
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}


val RepoTextFieldedShape = RoundedCornerShape(10.dp)
