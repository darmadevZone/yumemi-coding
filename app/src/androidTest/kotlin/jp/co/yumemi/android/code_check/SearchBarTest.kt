package jp.co.yumemi.android.code_check

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import jp.co.yumemi.android.code_check.components.SearchBar
import org.junit.Rule
import org.junit.Test

class SearchBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `MainScreen_SearchBarの表示`() {
        // 1. UIを表示
        composeTestRule.setContent {
            SearchBar(value = "", onChange = {}) {}
        }
        composeTestRule.onNodeWithText("Githubのリポジトリが検索できるよ...").assertIsDisplayed()
        composeTestRule.onNodeWithTag("textField").assertIsFocused()   // 表示されていることを確認する
    }
}