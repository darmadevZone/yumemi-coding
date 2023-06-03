package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    placeholder: String = "Githubのリポジトリが検索できるよ...",
    minLines: Int = 1,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    TextField(
        value = value,
        trailingIcon = {
            IconButton(
                onClick = {
                    focusManager.clearFocus()
                },
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "searchIcon")
        },
        onValueChange = onChange,
        modifier = modifier.focusRequester(focusRequester).testTag("textField"),
        colors = TextFieldDefaults.textFieldColors(),
        shape = RepoTextFieldedShape,
        maxLines = minLines,
        placeholder = { Text(text = placeholder, color = Color.Black.copy(alpha = 0.5f)) },
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}


val RepoTextFieldedShape = RoundedCornerShape(10.dp)
