package jp.co.yumemi.android.code_check

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RepoItem(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable
