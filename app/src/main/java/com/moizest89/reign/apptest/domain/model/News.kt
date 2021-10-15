package com.moizest89.reign.apptest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewsItem(
    var id: Int = 0,
    var title: String? = "",
    var url: String? = "",
    var author: String = "",
    var commentText: String = "",
    var numComments: Int = 0,
    var objectID: String = "",
    var parentId: Int = 0,
    var storyText: String = "",
    var createdAt: String = "",
    var createdAtI: Int = 0,
) : Parcelable