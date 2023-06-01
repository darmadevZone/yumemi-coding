package jp.co.yumemi.android.code_check.repository

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.yumemi.android.code_check.RepoItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.Exception

object  GithubRepository {
    var cacheRepoList:List<RepoItem> = listOf()


    fun getGithubSearch(quary:String): List<RepoItem> {
        var moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val githubSearvice = retrofit.create(GithubRepoService::class.java)

        try {
            val res = githubSearvice.listRepoItems(quary).execute().body()
            cacheRepoList = res!!.items
            Log.d("ResPonst", res.toString())
            return res!!.items
        } catch (e: Exception) {
            Log.d("Error1", e.toString())
            throw Exception()
        }
    }

    fun getRepoItem(id:Int):RepoItem?{
        Log.d("RepoItemList",cacheRepoList.toString())
        return cacheRepoList.first{it.id == id}
    }

}

interface GithubRepoService {
    @GET("repositories")
    fun listRepoItems(@Query("q") searchQuery: String): Call<RepoItemResponse>
}

data class RepoItemResponse(
    var items: List<RepoItem>,
)
