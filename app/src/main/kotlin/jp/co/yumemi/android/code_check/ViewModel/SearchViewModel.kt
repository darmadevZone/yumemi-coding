package jp.co.yumemi.android.code_check

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.yumemi.android.code_check.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception
import kotlin.concurrent.thread

class SearchViewModel : ViewModel() {
    var searchQuery = MutableStateFlow("")
    var repoList = MutableStateFlow<List<RepoItem>>(emptyList())

    fun changeQuery(query: String) {
        searchQuery.update { query }
    }

    fun getRepoData() {
        viewModelScope.launch(Dispatchers.IO) {
            var res = GithubRepository.getGithubSearch(searchQuery.value)
            repoList.update { res }
        }
    }
}

