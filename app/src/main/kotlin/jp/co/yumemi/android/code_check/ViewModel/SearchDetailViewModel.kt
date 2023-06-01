package jp.co.yumemi.android.code_check.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.RepoItem
import jp.co.yumemi.android.code_check.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchDetailViewModel() : ViewModel() {
    val repoItem = MutableStateFlow<RepoItem?>(null)

    fun getRepoItemData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var res = GithubRepository.getRepoItem(id)
            repoItem.update { res }
        }
    }
}

