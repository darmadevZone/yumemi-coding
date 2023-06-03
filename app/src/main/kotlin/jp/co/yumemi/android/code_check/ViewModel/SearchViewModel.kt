package jp.co.yumemi.android.code_check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

