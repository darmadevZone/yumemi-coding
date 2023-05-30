package jp.co.yumemi.android.code_check.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.yumemi.android.code_check.Owner
import jp.co.yumemi.android.code_check.RepoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.concurrent.thread

class RepoViewModel() : ViewModel() {
    val repoItem = MutableStateFlow<RepoItem?>(null)
    fun getRepoData(owner: Owner, repo: String) {

        try {
            var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            var retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

            val githubSearvice = retrofit.create(OwnerRepoService::class.java)

            thread {
                try {
                    val res =
                        githubSearvice.OwnerRepoItem(owner = owner, repo = repo).execute().body()
                    Log.d("OwnerRepo", res.toString())
//                    repoItem.update { }

                } catch (e: Exception) {
                    Log.d("Error", e.toString())
                }
            }
        } catch (e: Exception) {
            Log.d("Error2", e.message!!)
        }
    }

    interface OwnerRepoService {
        @GET("repos")
        fun OwnerRepoItem(@Query("q") owner: Owner,@Query("/") repo: String): Call<RepoItem>
    }
}

