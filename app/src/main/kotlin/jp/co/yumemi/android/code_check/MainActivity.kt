/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.components.SearchBar
import jp.co.yumemi.android.code_check.components.TopBar
import java.util.*

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var lastSearchDate: Date
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<SearchViewModel>()
            val searchRepo by viewModel.searchQuery.collectAsState()
            Scaffold(topBar = { TopBar() }) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(4.dp)) {
                    SearchBar(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxWidth(),
                        value =searchRepo,
                        onChange = { viewModel.changeQuery(it)})
                }
            }
        }
    }
}
