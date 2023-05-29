package jp.co.yumemi.android.code_check

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel:ViewModel() {
    var searchQuery  = MutableStateFlow("")

    fun changeQuery(query:String){
        searchQuery.update { query }
    }

    fun searchRepo(){

    }



}