package jp.co.yumemi.android.code_check

import android.util.Log
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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

    fun searchRepo() {

        var moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val githubSearvice = retrofit.create(GithubRepoService::class.java)

        thread {
            try {
                val res = githubSearvice.listRepoItems(searchQuery.value).execute().body()
                Log.d("ResPonst", res.toString())
                repoList.update { res!!.items }

            } catch (e: Exception) {
                Log.d("Error1", e.toString())
            }
        }

    }


}


interface GithubRepoService {
    @GET("repositories")
    fun listRepoItems(@Query("q") searchQuery: String): Call<RepoItemResponse>
}

data class RepoItemResponse(
    var items: List<RepoItem>,
//    var total_count: Int
)