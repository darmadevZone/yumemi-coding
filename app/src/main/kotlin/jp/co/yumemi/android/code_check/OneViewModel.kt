/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.content.Context
import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.co.yumemi.android.code_check.MainActivity.Companion.lastSearchDate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.util.*

/**
 * TwoFragment で使う
 */
class OneViewModel(
    val context: Context
) : ViewModel() {

    // 検索結果
    fun searchResults(inputText: String): List<RepoItem> = runBlocking {
        val client = HttpClient(Android)

        return@runBlocking GlobalScope.async {
            val response: HttpResponse = client!!.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonBody = JSONObject(response.receive<String>())

            val jsonRepoItems = jsonBody.optJSONArray("items")!!

            val RepoItems = mutableListOf<RepoItem>()

            /**
             * アイテムの個数分ループする
             */
            for (i in 0 until jsonRepoItems.length()) {
                val jsonRepoItem = jsonRepoItems.optJSONObject(i)!!
                val name = jsonRepoItem.optString("full_name")
                val ownerIconUrl = jsonRepoItem.optJSONObject("owner")!!.optString("avatar_url")
                val language = jsonRepoItem.optString("language")
                val stargazersCount = jsonRepoItem.optLong("stargazers_count")
                val watchersCount = jsonRepoItem.optLong("watchers_count")
                val forksCount = jsonRepoItem.optLong("forks_conut")
                val openIssuesCount = jsonRepoItem.optLong("open_issues_count")

                RepoItems.add(
                    RepoItem(
                        name = name,
                        ownerIconUrl = ownerIconUrl,
                        language = context.getString(R.string.written_language, language),
                        stargazersCount = stargazersCount,
                        watchersCount = watchersCount,
                        forksCount = forksCount,
                        openIssuesCount = openIssuesCount
                    )
                )
            }

            lastSearchDate = Date()

            return@async RepoItems.toList()
        }.await()
    }
}
