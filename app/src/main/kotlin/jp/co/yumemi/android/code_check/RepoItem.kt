package jp.co.yumemi.android.code_check

import android.os.Parcelable
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonQualifier
import kotlinx.android.parcel.Parcelize

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class OwnerIconUrl


@Parcelize
data class RepoItem(
    @Json(name = "id") val id: Int,
    @Json(name = "full_name") val name: String,
    @Json(name = "owner") val owner: Owner,
    @Json(name = "language") val language: String?,
    @Json(name = "stargazers_count") val stargazersCount: Long,
    @Json(name = "watchers") val watchersCount: Long,
    @Json(name = "forks") val forksCount: Long,
    @Json(name = "open_issues") val openIssuesCount: Long,
) : Parcelable

@Parcelize
data class Owner(
    val avatar_url: String,
    val repos_url:String
) : Parcelable